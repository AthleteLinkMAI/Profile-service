package team.anonyms.athletelink.profileservice.dto.service.kafka;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import team.anonyms.athletelink.profileservice.entity.User;

import java.util.UUID;

public record UserToUpdate(
        @NotNull UUID userId,
        @NotNull User.UserRole role,
        @NotBlank @Email String email,
        @NotNull Boolean isVerified,
        @NotNull Boolean isBanned
) {
}