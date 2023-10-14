package ru.araok.service;

import ru.araok.dto.MediaSubtitleDto;

public interface MediaSubtitleService {

    MediaSubtitleDto save(MediaSubtitleDto mediaSubtitleD);

    MediaSubtitleDto findMediaSubtitleByContentIdAndLanguageId(long contentId, long languageId);
}
