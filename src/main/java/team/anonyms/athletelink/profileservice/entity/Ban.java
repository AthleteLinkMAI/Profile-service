package team.anonyms.athletelink.profileservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ban {
    @Id
    private UUID userId;

    @MapsId
    @OneToOne(optional = false)
    private User user;
}
