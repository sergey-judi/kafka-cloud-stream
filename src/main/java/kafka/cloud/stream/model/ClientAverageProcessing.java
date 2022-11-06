package kafka.cloud.stream.model;

import lombok.Data;

@Data
public class ClientAverageProcessing {

  private double processingTimeMillis = 0;
  private long processedNumber = 0;

  public void update(long newlyProcessedTime) {
    this.processingTimeMillis =
        (this.processedNumber * this.processingTimeMillis + newlyProcessedTime) / ++this.processedNumber;
  }

}
