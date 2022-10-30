package kafka.cloud.stream.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@ConfigurationProperties("application.data")
public record ApplicationDataProperties(
    @NotNull Long randomValueBound,
    @NotNull List<@NotBlank String> operators,
    @NotNull List<@NotBlank String> clients
) {}
