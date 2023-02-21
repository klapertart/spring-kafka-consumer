package klapertart.lab.kafkaconsumer;

import klapertart.lab.kafkaconsumer.properties.KafkaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {
		KafkaProperties.class
})
public class SpringbootKafkaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootKafkaConsumerApplication.class, args);
	}

}
