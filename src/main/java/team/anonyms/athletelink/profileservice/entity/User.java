package team.anonyms.athletelink.profileservice.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
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
@Table(name = "users")
public class User {
    public enum UserRole {
        ADMIN,
        MODERATOR,
        USER
    }

    public enum MessagePolicy {
        NO_ONE,
        ONLY_FRIENDS,
        EVERYBODY
    }

    @Setter(AccessLevel.NONE)
    @Id
    private UUID id;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Enumerated(EnumType.STRING)
    @NotNull
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

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Enumerated(EnumType.STRING)
    @NotNull
    private MessagePolicy messagePolicy;

    @Setter(AccessLevel.NONE)
    @CreationTimestamp
    private LocalDate registrationDate;
}
