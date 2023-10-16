package ru.araok.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.araok.service.PropertyProvider;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties implements PropertyProvider {
    private final long countContentDownloads;
}
