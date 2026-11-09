package team.anonyms.athletelink.profileservice.dto.service.kafka;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import team.anonyms.athletelink.profileservice.entity.User;

import java.time.LocalDate;
import java.util.UUID;

public record UserToCreate(
        @NotNull UUID userId,
        @NotNull User.UserRole role,
        @NotBlank @Size(max = 30) String nickname,
        @Size(max = 30) String name,
        @Size(max = 30) String surname,
        @NotNull LocalDate birthdate,
        @NotBlank @Email String email,
        @NotNull Boolean isVerified,
        @NotNull LocalDate registrationDate,
        Boolean isBanned
) {
}