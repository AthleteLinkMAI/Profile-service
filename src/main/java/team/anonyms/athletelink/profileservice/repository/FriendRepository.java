package team.anonyms.athletelink.profileservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.anonyms.athletelink.profileservice.entity.Friend;
import team.anonyms.athletelink.profileservice.entity.UserAndFriendId;

@Repository
public interface FriendRepository extends JpaRepository<Friend, UserAndFriendId> {
}