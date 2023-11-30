package ru.araok.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.araok.dto.UserDto;

@FeignClient(name = "araok-authorization-service", path = "/auth")
public interface AuthorizationProxy {
    @GetMapping(value = "/user")
    UserDto findUserById(@RequestHeader("Authorization") String token);
}
