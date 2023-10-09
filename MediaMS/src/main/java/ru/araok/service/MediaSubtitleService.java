package ru.araok.service;

import ru.araok.domain.MediaSubtitle;
import ru.araok.dto.MediaSubtitleDto;

public interface MediaSubtitleService {
    MediaSubtitleDto findMediaSubtitleByContentId(long contentId);
}
