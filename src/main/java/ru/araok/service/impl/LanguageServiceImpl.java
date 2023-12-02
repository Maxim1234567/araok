package ru.araok.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.araok.dto.LanguageDto;
import ru.araok.repository.LanguageRepository;
import ru.araok.service.LanguageService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    @Override
    @Transactional(readOnly = true)
    public List<LanguageDto> getAll() {
        log.info("return all languages");

        return languageRepository.findAll().stream()
                .map(LanguageDto::toDto)
                .toList();
    }
}
