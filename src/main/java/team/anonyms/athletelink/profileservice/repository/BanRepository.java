package team.anonyms.athletelink.profileservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.anonyms.athletelink.profileservice.entity.Ban;

import java.util.UUID;

@Repository
public interface BanRepository extends JpaRepository<Ban, UUID> {
}
