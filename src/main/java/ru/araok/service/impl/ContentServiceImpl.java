package ru.araok.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.araok.domain.Content;
import ru.araok.domain.ContentMedia;
import ru.araok.domain.MediaSubtitle;
import ru.araok.dto.ContentDto;
import ru.araok.dto.ContentMediaDto;
import ru.araok.dto.ContentWithContentMediaAndMediaSubtitleDto;
import ru.araok.dto.MediaSubtitleDto;
import ru.araok.exception.NotFoundContentException;
import ru.araok.repository.ContentCounterRepository;
import ru.araok.repository.ContentMediaRepository;
import ru.araok.repository.ContentRecommendedRepository;
import ru.araok.repository.ContentRepository;
import ru.araok.repository.MediaSubtitleRepository;
import ru.araok.service.ContentService;
import ru.araok.service.PropertyProvider;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final PropertyProvider propertyProvider;

    private final ContentRepository contentRepository;

    private final ContentMediaRepository contentMediaRepository;

    private final MediaSubtitleRepository mediaSubtitleRepository;

    private final ContentCounterRepository contentCounterRepository;

    private final ContentRecommendedRepository contentRecommendedRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ContentDto> getAll() {
        return contentRepository.findAll().stream()
                .map(ContentDto::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContentDto> getNewContents() {
        return contentRepository.findByCreateDateLessThanNow().stream()
                .map(ContentDto::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContentDto> getPopularContents() {
        return contentCounterRepository.findByCountGreaterThan(propertyProvider.getCountContentDownloads()).stream()
                .map(contentCounter -> ContentDto.toDto(contentCounter.getContent()))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContentDto> findContentsByName(String name) {
        return contentRepository.findByNameContainingIgnoreCase(name).stream()
                .map(ContentDto::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ContentDto findContentById(Long id) {
        return contentRepository.findById(id)
                .map(ContentDto::toDto)
                .orElseThrow(NotFoundContentException::new);
    }

    @Override
    @Transactional
    public ContentDto save(ContentWithContentMediaAndMediaSubtitleDto content) {
        Content contentDomain = ContentDto.toDomainObject(content.getContent());
        ContentMedia preview = ContentMediaDto.toDomainObject(content.getPreview());
        ContentMedia video = ContentMediaDto.toDomainObject(content.getVideo());
        MediaSubtitle subtitle = MediaSubtitleDto.toDomainObject(content.getMediaSubtitle());

        Content newContent = contentRepository.save(contentDomain);

        preview.getContentMediaId().getContent().setId(newContent.getId());
        video.getContentMediaId().getContent().setId(newContent.getId());
        subtitle.getContent().setId(newContent.getId());

        contentMediaRepository.save(preview);
        contentMediaRepository.save(video);
        mediaSubtitleRepository.save(subtitle);

        return ContentDto.toDto(
                newContent
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContentDto> getRecommendedContents() {
        return contentRecommendedRepository.findAll().stream()
                .map(contentRecommended -> ContentDto.toDto(contentRecommended.getContent()))
                .toList();
    }
}
