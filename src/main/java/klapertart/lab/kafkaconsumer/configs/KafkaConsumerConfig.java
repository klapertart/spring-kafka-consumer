package klapertart.lab.kafkaconsumer.configs;

import klapertart.lab.kafkaconsumer.properties.KafkaProperties;
import klapertart.lab.kafkaconsumer.data.User;
import klapertart.lab.kafkaconsumer.properties.SslProperties;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.security.plain.PlainLoginModule;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kurakuraninja
 * @since 20/02/23
 */

@Configuration
public class KafkaConsumerConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Autowired
    private SslProperties sslProperties;

    // handle string


    @Bean
    public ConsumerFactory<String,String> stringConsumerFactory(){
        Map<String,Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,kafkaProperties.getConsumer().getAutoOffsetReset());
        configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,kafkaProperties.getEnableAutoCommitConfig());
        configs.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,kafkaProperties.getAutoCommitIntervalMsConfig());

        if(sslProperties.isEnabled()) {
            configs.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
            configs.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, sslProperties.getTruststore().getLocation());
            configs.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, sslProperties.getTruststore().getPassword());
            configs.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, sslProperties.getKeystore().getLocation());
            configs.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, sslProperties.getKeystore().getPassword());
            configs.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, sslProperties.getKey().getPassword());
            configs.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, "");
            configs.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
            configs.put(SaslConfigs.SASL_JAAS_CONFIG, PlainLoginModule.class.getName() + " required username=\"" + sslProperties.getJaas().getUsername() + "\" password=\"" + sslProperties.getJaas().getPassword() + "\";");
        }

        return new DefaultKafkaConsumerFactory<>(configs);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,String> stringListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stringConsumerFactory());
        return factory;
    }



    // handle object

    /*
    @Bean
    public ConsumerFactory<String, User> userConsumerFactory(){
        Map<String,Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getGroupId());
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        configs.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS,StringDeserializer.class);
        configs.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS,JsonDeserializer.class);
        configs.put(JsonDeserializer.TRUSTED_PACKAGES,kafkaProperties.getTrustedPackages());
        configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,kafkaProperties.getConsumer().getAutoOffsetReset());
        configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,kafkaProperties.getEnableAutoCommitConfig());
        configs.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,kafkaProperties.getAutoCommitIntervalMsConfig());

        if(sslProperties.isEnabled()) {
            configs.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
            configs.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, sslProperties.getTruststore().getLocation());
            configs.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, sslProperties.getTruststore().getPassword());
            configs.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, sslProperties.getKeystore().getLocation());
            configs.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, sslProperties.getKeystore().getPassword());
            configs.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, sslProperties.getKey().getPassword());
            configs.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, "");
            configs.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
            configs.put(SaslConfigs.SASL_JAAS_CONFIG, PlainLoginModule.class.getName() + " required username=\"" + sslProperties.getJaas().getUsername() + "\" password=\"" + sslProperties.getJaas().getPassword() + "\";");
        }

        return new DefaultKafkaConsumerFactory<>(configs,new StringDeserializer(),new JsonDeserializer<>(User.class,false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,User> userListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,User> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userConsumerFactory());
        return factory;
    }
     */
}
