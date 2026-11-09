package team.anonyms.athletelink.profileservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@EnableConfigurationProperties(KafkaTopicsProperties.class)
@Configuration
public class KafkaConfiguration {
    @Bean
    public NewTopic friendsChangesTopic(KafkaTopicsProperties topics) {
        return TopicBuilder
                .name(topics.friendsChanges())
                .partitions(3)
                .replicas(1)
                .build();
    }
}
