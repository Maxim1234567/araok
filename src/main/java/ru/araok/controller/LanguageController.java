package ru.araok.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.araok.dto.LanguageDto;
import ru.araok.service.LanguageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LanguageController {
    private final LanguageService languageService;

    @GetMapping("/api/language")
    public ResponseEntity<List<LanguageDto>> getAll() {
        return ResponseEntity.ok(languageService.getAll());
    }
}
