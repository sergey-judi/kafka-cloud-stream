package kafka.cloud.stream.service.kafka;

import com.kafka.processing.avro.model.ProcessingKey;
import com.kafka.processing.avro.model.ProcessingValue;
import kafka.cloud.stream.service.domain.ProcessingTimeService;
import kafka.cloud.stream.util.KafkaMessageExtractor;
import kafka.cloud.stream.util.ProcessingTimeLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service("kafka-message-consumer")
@RequiredArgsConstructor
public class KafkaMessageConsumer implements Consumer<Message<ProcessingValue>> {

  private final KafkaMessageExtractor kafkaMessageExtractor;
  private final ProcessingTimeService processingTimeService;

  @Override
  public void accept(Message<ProcessingValue> kafkaMessage) {
    ProcessingKey key = kafkaMessageExtractor.getMessageKey(kafkaMessage, ProcessingKey.class);
    ProcessingValue value = kafkaMessage.getPayload();

    processingTimeService.updateAverageForOperator(
        key.getOperator(),
        value.getProcessingTimeMillis()
    );

    ProcessingTimeLogger.logAverageProcessingPerOperator(
        processingTimeService.getAverageProcessingTimePerOperator()
    );
  }

}
