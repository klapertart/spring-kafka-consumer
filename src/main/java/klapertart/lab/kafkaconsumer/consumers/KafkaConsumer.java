package klapertart.lab.kafkaconsumer.consumers;
import klapertart.lab.kafkaconsumer.data.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author kurakuraninja
 * @since 20/02/23
 */

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.group-id}", containerFactory = "stringListenerContainerFactory")
    public void receiveMessage(String message){
        log.info("### Receive String Message >> {}", message);
    }

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.group-id}", containerFactory = "userListenerContainerFactory")
    public void receiveUser(User user){
        log.info("### Receive User Message >> {}", user.toString());
    }

}
