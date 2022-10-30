package kafka.cloud.stream;

import kafka.cloud.stream.properties.ApplicationDataProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(ApplicationDataProperties.class)
@SpringBootApplication
public class KafkaCloudStreamApplication {

  public static void main(String[] args) {
    SpringApplication.run(KafkaCloudStreamApplication.class, args);
  }

}
