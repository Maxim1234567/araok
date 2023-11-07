package ru.araok.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.araok.dto.MediaSubtitleDto;
import ru.araok.exception.NotFoundContentException;
import ru.araok.repository.MediaSubtitleRepository;
import ru.araok.service.MediaSubtitleService;

@Slf4j
@Service
@RequiredArgsConstructor
public class MediaSubtitleServiceImpl implements MediaSubtitleService {
    private final MediaSubtitleRepository mediaSubtitleRepository;

    @Override
    @Transactional
    public MediaSubtitleDto save(MediaSubtitleDto mediaSubtitle) {
        log.info("save media subtitle");

        return MediaSubtitleDto.toDto(
                mediaSubtitleRepository.save(
                        MediaSubtitleDto.toDomainObject(mediaSubtitle)
                )
        );
    }

    @Override
    @Transactional(readOnly = true)
    public MediaSubtitleDto findMediaSubtitleByContentIdAndLanguageId(long contentId, long languageId) {
        log.info("find media subtitle by content id {} and language id {}", contentId, languageId);

        return mediaSubtitleRepository
                .findByContentIdAndLanguageId(contentId, languageId)
                .map(MediaSubtitleDto::toDto)
                .orElseThrow(NotFoundContentException::new);
    }
}
