package team.anonyms.athletelink.profileservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import team.anonyms.athletelink.profileservice.dto.service.kafka.UserToCreate;
import team.anonyms.athletelink.profileservice.dto.service.kafka.UserToDelete;
import team.anonyms.athletelink.profileservice.dto.service.kafka.UserToUpdate;

@SuppressWarnings(value = {"unused"})
@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaConsumerService {
    private final UserService userService;

    @KafkaListener(topics = "${app.kafka.topics.users-to-create}", groupId = "${spring.application.name}")
    public void receiveUserToCreate(UserToCreate userToCreate) {
        log.atInfo()
                .setMessage("Message received")
                .addKeyValue("topic", "${app.kafka.topics.users-to-create}")
                .addKeyValue("userToCreate", userToCreate)
                .log();

        userService.createUser(userToCreate);
    }

    @KafkaListener(topics = "${app.kafka.topics.users-to-delete}", groupId = "${spring.application.name}")
    public void receiveUserToDelete(UserToDelete userToDelete) {
        log.atInfo()
                .setMessage("Message received")
                .addKeyValue("topic", "${app.kafka.topics.users-to-delete}")
                .addKeyValue("userToDelete", userToDelete)
                .log();

        userService.deleteUser(userToDelete);
    }

    @KafkaListener(topics = "${app.kafka.topics.users-to-update}", groupId = "${spring.application.name}")
    public void receiveUserToUpdate(UserToUpdate userToUpdate) {
        log.atInfo()
                .setMessage("Message received")
                .addKeyValue("topic", "${app.kafka.topics.users-to-update}")
                .addKeyValue("userToUpdate", userToUpdate)
                .log();

        userService.updateUser(userToUpdate);
    }
}
