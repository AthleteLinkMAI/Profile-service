package team.anonyms.athletelink.profileservice.dto.controller.user;

import team.anonyms.athletelink.profileservice.entity.User;

import java.time.LocalDate;
import java.util.UUID;

public record UserControllerDto(
        UUID id,
        User.UserRole role,
        String nickname,
        String name,
        String surname,
        String email,
        Boolean isVerified,
        Integer age,
        LocalDate registrationDate
) {
}
