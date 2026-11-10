package team.anonyms.athletelink.profileservice.dto.controller.user;

import team.anonyms.athletelink.profileservice.entity.User;

import java.time.LocalDate;
import java.util.UUID;

public record UserPublicControllerDto(
        UUID id,
        User.UserRole role,
        String nickname,
        String name,
        String surname,
        Boolean isVerified,
        User.MessagePolicy messagePolicy,
        Integer age,
        LocalDate registrationDate
) {
}
