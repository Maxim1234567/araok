package ru.araok.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.araok.property.ApplicationProperties;

@Configuration
@EnableConfigurationProperties(ApplicationProperties.class)
public class ApplicationConfig {
}
