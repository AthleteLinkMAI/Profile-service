package team.anonyms.athletelink.profileservice.dto.service.user;

import java.time.LocalDate;
import java.util.UUID;

public record UserToUpdateServiceDto(
        UUID userId,
        String nickname,
        String name,
        String surname,
        LocalDate birthdate
) {
}
