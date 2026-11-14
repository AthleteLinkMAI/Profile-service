package team.anonyms.athletelink.profileservice.dto.service.kafka;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserToDelete(
        @NotNull UUID userId
) {
}