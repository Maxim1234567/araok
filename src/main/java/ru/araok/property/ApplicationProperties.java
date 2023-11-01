package ru.araok.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.pqc.crypto.util.PQCOtherInfoGenerator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.w3c.dom.stylesheets.LinkStyle;
import ru.araok.service.PropertyProvider;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties implements PropertyProvider {
    private final long countContentDownloads;

    private final List<RequestProperties> requests;
}
