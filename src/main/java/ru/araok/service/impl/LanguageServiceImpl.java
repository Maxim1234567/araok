package ru.araok.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.araok.dto.LanguageDto;
import ru.araok.repository.LanguageRepository;
import ru.araok.service.LanguageService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    @Override
    public List<LanguageDto> getAll() {
        return languageRepository.findAll().stream()
                .map(LanguageDto::toDto)
                .toList();
    }
}
