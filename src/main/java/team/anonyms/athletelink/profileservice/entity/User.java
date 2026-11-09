package team.anonyms.athletelink.profileservice.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    public enum UserRole {
        ADMIN,
        MODERATOR,
        USER
    }

    @Setter(AccessLevel.NONE)
    @Id
    private UUID id;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Size(max = 30)
    @NotBlank
    private String nickname;

    @Nullable
    private String name;

    @Nullable
    private String surname;

    @NotNull
    private LocalDate birthdate;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private Boolean isVerified;

    @Setter(AccessLevel.NONE)
    @CreationTimestamp
    private LocalDate registrationDate;
}
