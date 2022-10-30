package kafka.cloud.stream.service.domain;

import kafka.cloud.stream.model.ClientAverageProcessing;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessingTimeService {

  @Getter
  private final Map<String, ClientAverageProcessing> averageProcessingTimePerOperator = new HashMap<>();

  public void updateAverageForOperator(String operator, long clientProcessingTime) {
    averageProcessingTimePerOperator
        .computeIfAbsent(operator, k -> new ClientAverageProcessing())
        .update(clientProcessingTime);
  }

}
