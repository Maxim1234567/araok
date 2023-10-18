package ru.araok.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.araok.dto.MediaSubtitleDto;
import ru.araok.exception.NotFoundContentException;
import ru.araok.repository.MediaSubtitleRepository;
import ru.araok.service.MediaSubtitleService;

@Service
@RequiredArgsConstructor
public class MediaSubtitleServiceImpl implements MediaSubtitleService {
    private final MediaSubtitleRepository mediaSubtitleRepository;

    @Override
    @Transactional
    public MediaSubtitleDto save(MediaSubtitleDto mediaSubtitle) {
        return MediaSubtitleDto.toDto(
                mediaSubtitleRepository.save(
                        MediaSubtitleDto.toDomainObject(mediaSubtitle)
                )
        );
    }

    @Override
    @Transactional(readOnly = true)
    public MediaSubtitleDto findMediaSubtitleByContentIdAndLanguageId(long contentId, long languageId) {
        return mediaSubtitleRepository
                .findByContentIdAndLanguageId(contentId, languageId)
                .map(MediaSubtitleDto::toDto)
                .orElseThrow(NotFoundContentException::new);
    }
}
