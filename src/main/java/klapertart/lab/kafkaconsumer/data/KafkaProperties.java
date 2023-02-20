package klapertart.lab.kafkaconsumer.data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author kurakuraninja
 * @since 20/02/23
 */

@ConfigurationProperties("spring.kafka")
@Component
@Getter
@Setter
public class KafkaProperties {
    private String bootstrapServers;
    private String topic;
    private String groupId;
}
