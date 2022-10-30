package kafka.cloud.stream.logging;

import org.apache.logging.log4j.util.Strings;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.ChannelInterceptor;

import static java.util.Objects.isNull;

public abstract class AbstractChannelInterceptorAdapter implements ChannelInterceptor {

  private static final String LINE_SEPARATOR = "\n";

  protected String composeLogMessage(Message<?> message, DirectChannel channel, String heading) {
    return heading.concat(LINE_SEPARATOR)
        + getLogMessage("Function", channel.getBeanName()).concat(LINE_SEPARATOR)
        + getLogMessage("Headers", message.getHeaders()).concat(LINE_SEPARATOR)
        + getLogMessage("Payload", getPayload(message)).concat(LINE_SEPARATOR);
  }

  private <T> String getLogMessage(String name, T content) {
    return isNull(content)
        ? Strings.EMPTY
        : "%s: %s".formatted(name, content);
  }

  private String getPayload(Message<?> message) {
    return message.getPayload().getClass().equals(byte[].class)
        ? new String((byte[]) message.getPayload())
        : message.getPayload().toString();
  }

}
