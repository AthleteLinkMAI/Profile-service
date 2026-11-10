package team.anonyms.athletelink.profileservice.dto.service.user;

import team.anonyms.athletelink.profileservice.entity.User;

import java.time.LocalDate;
import java.util.UUID;

public record UserServiceDto(
        UUID id,
        User.UserRole role,
        String nickname,
        String name,
        String surname,
        String email,
        Boolean isVerified,
        User.MessagePolicy messagePolicy,
        Integer age,
        LocalDate registrationDate
) {
}
