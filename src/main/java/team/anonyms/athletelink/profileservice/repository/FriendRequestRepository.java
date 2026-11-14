package team.anonyms.athletelink.profileservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.anonyms.athletelink.profileservice.entity.FriendRequest;
import team.anonyms.athletelink.profileservice.entity.UserAndFriendId;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, UserAndFriendId> {
}