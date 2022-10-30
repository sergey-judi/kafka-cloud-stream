package kafka.cloud.stream.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.GlobalChannelInterceptor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty("logging.pattern.output")
@GlobalChannelInterceptor(patterns = "#{environment.getProperty('logging.pattern.output')}")
public class OutputChannelInterceptor extends AbstractChannelInterceptorAdapter {

  @Override
  public Message<?> preSend(Message<?> message, MessageChannel channel) {
    log.info(
        composeLogMessage(
            message,
            (DirectChannel) channel,
            "============== OUTGOING message =============="
        )
    );

    return message;
  }

}
