package team.anonyms.athletelink.profileservice.dto.service.kafka;

import team.anonyms.athletelink.profileservice.entity.User;

import java.util.UUID;

public record ProfileUpdate(
        UUID userId,
        User.MessagePolicy messagePolicy
) {
}
