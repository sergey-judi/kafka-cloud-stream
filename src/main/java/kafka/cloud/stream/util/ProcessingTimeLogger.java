package kafka.cloud.stream.util;

import kafka.cloud.stream.model.ClientAverageProcessing;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@UtilityClass
public class ProcessingTimeLogger {

  public final String ROW_TEMPLATE_REGULAR = "| %10s | %10s | %10s |%n";
  public final String ROW_TEMPLATE_AVERAGE_NUMERIC = "| %10s | %10s | %10.2f |%n";
  public final String ROW_SEPARATOR = "+------------+------------+------------+\n";
  public final String TABLE_HEADER = ROW_TEMPLATE_REGULAR.formatted("Operator", "Processed", "Avg. time");

  public void logAverageProcessingPerOperator(Map<String, ClientAverageProcessing> averageProcessingTimePerOperator) {
    String formattedProcessingInfo = averageProcessingTimePerOperator.entrySet()
        .stream()
        .map(entry -> format(entry.getKey(), entry.getValue()))
        .collect(Collectors.joining());

    log.info(
        "Average processing time per operator table\n"
            + ROW_SEPARATOR
            + TABLE_HEADER
            + ROW_SEPARATOR
            + formattedProcessingInfo
            + ROW_SEPARATOR
    );
  }

  private String format(String operator, ClientAverageProcessing averageInfo) {
    return ROW_TEMPLATE_AVERAGE_NUMERIC.formatted(
        operator,
        averageInfo.getProcessedNumber(),
        averageInfo.getProcessingTimeMillis()
    );
  }

}
