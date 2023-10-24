package ru.araok.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.araok.dto.AgeLimitDto;
import ru.araok.service.AgeLimitService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AgeLimitController {
    private final AgeLimitService ageLimitService;

    @GetMapping("/api/limit")
    public ResponseEntity<List<AgeLimitDto>> getAll() {
        return ResponseEntity.ok(ageLimitService.getAll());
    }
}
