package kafka.cloud.stream.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.avro.generic.GenericRecord;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class KafkaMessageExtractor {

  private final ObjectMapper objectMapper;

  @SneakyThrows
  public <T> T getMessageKey(Message<? extends GenericRecord> message, Class<T> targetType) {
    GenericRecord rawMessageKey = Objects.requireNonNull(
        message.getHeaders().get(KafkaHeaders.RECEIVED_KEY, GenericRecord.class)
    );

    return objectMapper.readValue(rawMessageKey.toString(), targetType);
  }

  @SneakyThrows
  public <T> T getMessageValue(Message<? extends GenericRecord> message, Class<T> targetType) {
    return objectMapper.readValue(message.getPayload().toString(), targetType);
  }

}
