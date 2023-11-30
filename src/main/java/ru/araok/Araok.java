package ru.araok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ru.araok.client.AuthorizationProxy;

@EnableFeignClients(basePackageClasses = AuthorizationProxy.class)
@SpringBootApplication
public class Araok {
    public static void main(String[] args) {
        SpringApplication.run(Araok.class, args);
    }

}
