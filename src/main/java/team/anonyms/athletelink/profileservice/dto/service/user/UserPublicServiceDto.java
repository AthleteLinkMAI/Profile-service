package team.anonyms.athletelink.profileservice.dto.service.user;

import team.anonyms.athletelink.profileservice.entity.User;

import java.time.LocalDate;
import java.util.UUID;

public record UserPublicServiceDto(
        UUID id,
        User.UserRole role,
        String nickname,
        String name,
        String surname,
        Integer age,
        LocalDate registrationDate
) {
}
