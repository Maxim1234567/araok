package ru.araok.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.araok.dto.AgeLimitDto;
import ru.araok.repository.AgeLimitRepository;
import ru.araok.service.AgeLimitService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AgeLimitServiceImpl implements AgeLimitService {
    private final AgeLimitRepository ageLimitRepository;


    @Override
    @Transactional(readOnly = true)
    public List<AgeLimitDto> getAll() {
        log.info("return all age limit");

        return ageLimitRepository.findAll()
                .stream()
                .map(AgeLimitDto::toDto)
                .toList();
    }
}
