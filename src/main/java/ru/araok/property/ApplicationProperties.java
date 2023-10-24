package ru.araok.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.pqc.crypto.util.PQCOtherInfoGenerator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.araok.service.PropertyProvider;

@Getter
//@RequiredArgsConstructor
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties implements PropertyProvider {
    private final long countContentDownloads;

    public ApplicationProperties(long countContentDownloads) {
        System.out.println("countContentDownloads: " + countContentDownloads);
        this.countContentDownloads = countContentDownloads;
    }
}
