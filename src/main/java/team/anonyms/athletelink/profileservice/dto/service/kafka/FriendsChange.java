package team.anonyms.athletelink.profileservice.dto.service.kafka;

import java.util.UUID;

public record FriendsChange(
        FriendChangeType type,
        UUID userId,
        UUID friendId
) {
    public enum FriendChangeType {
        CREATE,
        DELETE
    }
}
