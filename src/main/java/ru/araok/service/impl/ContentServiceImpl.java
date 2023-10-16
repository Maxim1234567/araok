package ru.araok.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.araok.domain.Content;
import ru.araok.dto.ContentDto;
import ru.araok.exception.NotFoundContentException;
import ru.araok.repository.ContentCounterRepository;
import ru.araok.repository.ContentRecommendedRepository;
import ru.araok.repository.ContentRepository;
import ru.araok.service.ContentService;
import ru.araok.service.PropertyProvider;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final PropertyProvider propertyProvider;

    private final ContentRepository contentRepository;

    private final ContentCounterRepository contentCounterRepository;

    private final ContentRecommendedRepository contentRecommendedRepository;

    @Override
    public List<ContentDto> getAll() {
        return contentRepository.findAll().stream()
                .map(ContentDto::toDto)
                .toList();
    }

    @Override
    public List<ContentDto> getNewContents() {
        return contentRepository.findByCreateDateLessThanNow().stream()
                .map(ContentDto::toDto)
                .toList();
    }

    @Override
    public List<ContentDto> getPopularContents() {
        return contentCounterRepository.findByCountGreaterThan(propertyProvider.getCountContentDownloads()).stream()
                .map(contentCounter -> ContentDto.toDto(contentCounter.getContent()))
                .toList();
    }

    @Override
    public List<ContentDto> findContentsByName(String name) {
        return contentRepository.findByNameContainingIgnoreCase(name).stream()
                .map(ContentDto::toDto)
                .toList();
    }

    @Override
    public ContentDto findContentById(Long id) {
        return contentRepository.findById(id)
                .map(ContentDto::toDto)
                .orElseThrow(NotFoundContentException::new);
    }

    @Override
    public ContentDto save(ContentDto content) {
        Content contentDomain = ContentDto.toDomainObject(content);

        return ContentDto.toDto(
                contentRepository.save(contentDomain)
        );
    }

    @Override
    public List<ContentDto> getRecommendedContents() {
        return contentRecommendedRepository.findAll().stream()
                .map(contentRecommended -> ContentDto.toDto(contentRecommended.getContent()))
                .toList();
    }
}
