package team.anonyms.athletelink.profileservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team.anonyms.athletelink.profileservice.entity.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByNickname(String nickname);

    @Query("""
            SELECT u FROM User u
            WHERE u.id IN (SELECT f.friend.id FROM Friend f WHERE f.user.id = :userId)
            OR u.id IN (SELECT f.user.id FROM Friend f WHERE f.friend.id = :userId)
    """)
    Page<User> findFriendsByUserId(UUID userId, Pageable pageable);

    @Query("""
            SELECT u FROM User u
            WHERE u.id IN (
                SELECT fr.user.id FROM FriendRequest fr WHERE fr.friend.id = :userId
            )
    """)
    Page<User> findIncomingFriendRequestSendersByUserId(UUID userId, Pageable pageable);

    @Query("""
            SELECT u FROM User u
            WHERE u.id IN (
                SELECT fr.friend.id FROM FriendRequest fr WHERE fr.user.id = :userId
            )
    """)
    Page<User> findOutgoingFriendRequestReceiversByUserId(UUID userId, Pageable pageable);
}