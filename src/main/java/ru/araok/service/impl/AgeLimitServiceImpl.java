package ru.araok.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.araok.dto.AgeLimitDto;
import ru.araok.repository.AgeLimitRepository;
import ru.araok.service.AgeLimitService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgeLimitServiceImpl implements AgeLimitService {
    private final AgeLimitRepository ageLimitRepository;


    @Override
    public List<AgeLimitDto> getAll() {
        return ageLimitRepository.findAll()
                .stream()
                .map(AgeLimitDto::toDto)
                .toList();
    }
}
