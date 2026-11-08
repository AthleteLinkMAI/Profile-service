package team.anonyms.athletelink.profileservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.anonyms.athletelink.profileservice.dto.service.user.UserPublicServiceDto;
import team.anonyms.athletelink.profileservice.entity.User;
import team.anonyms.athletelink.profileservice.repository.FriendRepository;
import team.anonyms.athletelink.profileservice.repository.FriendRequestRepository;
import team.anonyms.athletelink.profileservice.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FriendService {
    private final FriendRepository friendRepository;
    private final FriendRequestRepository friendRequestRepository;
    private final UserRepository userRepository;

    // TODO: add modern pagination
    public List<UserPublicServiceDto> getPaginatedFriendsByUserId(UUID userId, int page, int size) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User is not found; id=" + userId)
        );

        return friendRepository.findAllOtherFriendsByUser(user).stream().map(
                friend -> {
                    User friendToMap = friend.getUser().getId().equals(userId) ? friend.getFriend() : friend.getUser();

                    return new UserPublicServiceDto(
                            friendToMap.getId(),
                            friendToMap.getRole(),
                            friendToMap.getNickname(),
                            friendToMap.getName(),
                            friendToMap.getSurname(),
                            UserService.countUserAge(friendToMap),
                            friendToMap.getRegistrationDate()
                    );
                }
        ).toList();
    }

    public void deleteFriend(UUID userId, UUID friendId) {

    }

    public void sendFriendRequest(UUID userId, UUID userToSendRequest) {

    }

    public void acceptFriendRequest(UUID userId, UUID friendId) {

    }

    public void declineFriendRequest(UUID userId, UUID friendId) {

    }

    public void revokeFriendRequest(UUID userId, UUID friendId) {

    }
}
