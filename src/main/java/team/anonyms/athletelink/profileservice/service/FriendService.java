package team.anonyms.athletelink.profileservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.anonyms.athletelink.profileservice.dto.service.kafka.FriendsChange;
import team.anonyms.athletelink.profileservice.dto.service.user.UserPublicServiceDto;
import team.anonyms.athletelink.profileservice.entity.Friend;
import team.anonyms.athletelink.profileservice.entity.FriendRequest;
import team.anonyms.athletelink.profileservice.entity.UserAndFriendId;
import team.anonyms.athletelink.profileservice.exception.EntityAlreadyExistsException;
import team.anonyms.athletelink.profileservice.mapper.UserMapper;
import team.anonyms.athletelink.profileservice.repository.FriendRepository;
import team.anonyms.athletelink.profileservice.repository.FriendRequestRepository;
import team.anonyms.athletelink.profileservice.repository.UserRepository;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FriendService {
    private final KafkaProducerService kafkaProducerService;
    private final FriendRepository friendRepository;
    private final FriendRequestRepository friendRequestRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private void requireUserExists(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User is not found; id=" + userId);
        }
    }

    public Page<UserPublicServiceDto> getPaginatedIncomingFriendRequestsByUserId(UUID userId, Pageable pageable) {
        requireUserExists(userId);

        return userRepository.findIncomingFriendRequestSendersByUserId(userId, pageable)
                .map(userMapper::userEntityToUserPublicServiceDto);
    }

    public Page<UserPublicServiceDto> getPaginatedOutcomingFriendRequestsByUserId(UUID userId, Pageable pageable) {
        requireUserExists(userId);

        return userRepository.findOutgoingFriendRequestReceiversByUserId(userId, pageable)
                .map(userMapper::userEntityToUserPublicServiceDto);
    }

    public Page<UserPublicServiceDto> getPaginatedFriendsByUserId(UUID userId, Pageable pageable) {
        requireUserExists(userId);

        return userRepository.findFriendsByUserId(userId, pageable)
                .map(userMapper::userEntityToUserPublicServiceDto);
    }

    public void deleteFriend(UUID userId, UUID friendId) {
        Friend friendToDelete = friendRepository.findById(new UserAndFriendId(userId, friendId)).orElseThrow(
                () -> new EntityNotFoundException("Friend is not found; userId=" + userId + " ;friendId="+friendId)
        );

        friendRepository.delete(friendToDelete);
        kafkaProducerService.sendFriendsChange(
                new FriendsChange(
                        FriendsChange.FriendChangeType.DELETE,
                        friendToDelete.getUser().getId(),
                        friendToDelete.getFriend().getId()
                )
        );
    }

    public void sendFriendRequest(UUID userId, UUID userToSendFriendRequestId) {
        if (!userRepository.existsById(userToSendFriendRequestId)) {
            throw new EntityNotFoundException(
                    "User to send friend request is not found; id=" + userToSendFriendRequestId
            );
        }

        UserAndFriendId id = new UserAndFriendId(userId, userToSendFriendRequestId);

        if (friendRequestRepository.existsById(id)) {
            throw new EntityAlreadyExistsException(
                    "Friend request already exists; userId=" + userId +
                            "userToSendFriendRequestId=" + userToSendFriendRequestId
            );
        }

        friendRequestRepository.save(
                FriendRequest.builder()
                        .id(id)
                        .build()
        );
    }

    @Transactional
    public void acceptFriendRequest(UUID userId, UUID friendId) {
        UserAndFriendId id = new UserAndFriendId(userId, friendId);

        if (!friendRequestRepository.existsById(id)) {
            throw new EntityNotFoundException("FriendRequest is not found; id=" + id);
        }

        friendRequestRepository.deleteById(id);
        Friend friendCreated = friendRepository.save(
                Friend.builder()
                        .id(id)
                        .build()
        );

        kafkaProducerService.sendFriendsChange(
                new FriendsChange(
                        FriendsChange.FriendChangeType.CREATE,
                        friendCreated.getUser().getId(),
                        friendCreated.getFriend().getId()
                )
        );
    }

    public void declineFriendRequest(UUID userId, UUID friendId) {
        friendRequestRepository.deleteById(new UserAndFriendId(userId, friendId));
    }

    public void revokeFriendRequest(UUID userId, UUID friendId) {
        friendRequestRepository.deleteById(new UserAndFriendId(userId, friendId));
    }
}
