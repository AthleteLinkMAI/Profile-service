package team.anonyms.athletelink.profileservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.anonyms.athletelink.profileservice.entity.Friend;
import team.anonyms.athletelink.profileservice.entity.User;
import team.anonyms.athletelink.profileservice.entity.UserAndFriendId;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, UserAndFriendId> {
    List<Friend> findAllFriendsByUser(User user);

    List<Friend> findAllFriendsByFriend(User friend);

    default List<Friend> findAllOtherFriendsByUser(User user) {
        List<Friend> friends = findAllFriendsByUser(user);

        friends.addAll(findAllFriendsByFriend(user));
        return friends.stream().distinct().toList();
    }
}
