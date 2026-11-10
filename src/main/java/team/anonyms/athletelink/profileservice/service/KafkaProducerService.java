package team.anonyms.athletelink.profileservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import team.anonyms.athletelink.profileservice.config.KafkaTopicsProperties;
import team.anonyms.athletelink.profileservice.dto.service.kafka.FriendsChange;
import team.anonyms.athletelink.profileservice.dto.service.kafka.ProfileUpdate;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class KafkaProducerService {
    private final KafkaTemplate<UUID, Object> kafkaTemplate;
    private final KafkaTopicsProperties topics;

    public void sendFriendsChange(FriendsChange friendsChange) {
        kafkaTemplate.send(topics.friendsChanges(), friendsChange.userId(), friendsChange);
    }

    public void sendProfileUpdate(ProfileUpdate profileUpdate) {
        kafkaTemplate.send(topics.profileUpdates(), profileUpdate.userId(), profileUpdate);
    }
}
