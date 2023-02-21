package klapertart.lab.kafkaconsumer.properties;

import lombok.Data;
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
@Data
public class KafkaProperties {
    private String bootstrapServers;
    private String groupId;
    private String topic;
    private ConsumerProperties consumer;
    private String enableAutoCommitConfig;
    private String autoCommitIntervalMsConfig;
    private String trustedPackages;

    @Getter
    @Setter
    @Data
    public static class ConsumerProperties{
        private String autoOffsetReset;
    }
}
