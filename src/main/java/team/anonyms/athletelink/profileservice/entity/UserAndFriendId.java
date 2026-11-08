package team.anonyms.athletelink.profileservice.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public record UserAndFriendId(
        UUID userId,
        UUID friendId
) implements Serializable {
}
