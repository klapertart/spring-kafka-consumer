package klapertart.lab.kafkaconsumer.properties;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author kurakuraninja
 * @since 21/02/23
 */

@Slf4j
@SpringBootTest
public class KafkaPropertiesTest {
    @Autowired
    private KafkaProperties kafkaProperties;

    @Test
    void testPrintClass() {
        log.info("### KAFKA PROPERTIES : {}",kafkaProperties.toString());
    }
}
