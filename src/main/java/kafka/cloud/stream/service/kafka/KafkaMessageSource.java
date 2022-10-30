package kafka.cloud.stream.service.kafka;

import com.kafka.processing.avro.model.ProcessingKey;
import com.kafka.processing.avro.model.ProcessingValue;
import kafka.cloud.stream.properties.ApplicationDataProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

@Service("kafka-message-producer")
@RequiredArgsConstructor
public class KafkaMessageSource implements Supplier<Message<ProcessingValue>> {

  private final ApplicationDataProperties applicationDataProperties;

  @Override
  public Message<ProcessingValue> get() {
    String operator = getRandomElement(applicationDataProperties.operators());
    String client = getRandomElement(applicationDataProperties.clients());

    ProcessingKey messageKey = new ProcessingKey(operator, client);
    ProcessingValue messageValue = new ProcessingValue(getRandomLong());

    return MessageBuilder.withPayload(messageValue)
        .setHeader(KafkaHeaders.KEY, messageKey)
        .build();
  }

  private <T> T getRandomElement(List<T> list) {
    int randomIndex = getRandomInt(list.size());
    return list.get(randomIndex);
  }

  private long getRandomLong() {
    return ThreadLocalRandom.current().nextLong(applicationDataProperties.randomValueBound());
  }

  private int getRandomInt(int bound) {
    return ThreadLocalRandom.current().nextInt(bound);
  }

}
