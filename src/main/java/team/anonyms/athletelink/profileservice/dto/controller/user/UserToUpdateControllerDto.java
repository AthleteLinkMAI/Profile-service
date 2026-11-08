package team.anonyms.athletelink.profileservice.dto.controller.user;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserToUpdateControllerDto(
        @Size(max = 30)
        @NotBlank
        String nickname,

        @Size(max = 30)
        @Nullable
        String name,

        @Size(max = 30)
        @Nullable
        String surname,

        @NotNull
        LocalDate birthdate
) {
}
