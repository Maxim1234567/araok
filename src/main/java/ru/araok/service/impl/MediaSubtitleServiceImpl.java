package ru.araok.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.araok.domain.MediaSubtitle;
import ru.araok.dto.MediaSubtitleDto;
import ru.araok.exception.NotFoundContentException;
import ru.araok.repository.MediaSubtitleRepository;
import ru.araok.service.MediaSubtitleService;

@Service
@RequiredArgsConstructor
public class MediaSubtitleServiceImpl implements MediaSubtitleService {
    private final MediaSubtitleRepository mediaSubtitleRepository;

    @Override
    public MediaSubtitleDto save(MediaSubtitleDto mediaSubtitle) {
        return MediaSubtitleDto.toDto(
                mediaSubtitleRepository.save(
                        MediaSubtitleDto.toDomainObject(mediaSubtitle)
                )
        );
    }

    @Override
    public MediaSubtitleDto findMediaSubtitleByContentIdAndLanguageId(long contentId, long languageId) {
        return mediaSubtitleRepository
                .findByContentIdAndLanguageId(contentId, languageId)
                .map(MediaSubtitleDto::toDto)
                .orElseThrow(NotFoundContentException::new);
    }
}
