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
    public MediaSubtitleDto findMediaSubtitleByContentIdAndLanguageId(long contentId, long languageId) {
        MediaSubtitle mediaSubtitle = mediaSubtitleRepository
                .findByContentIdAndLanguageId(contentId, languageId)
                .orElseThrow(NotFoundContentException::new);

        return MediaSubtitleDto.toDto(
                mediaSubtitle
        );
    }
}
