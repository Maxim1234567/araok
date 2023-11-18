package ru.araok.service;

import ru.araok.dto.LanguageDto;
import ru.araok.dto.MediaSubtitleDto;

import java.util.List;

public interface MediaSubtitleService {

    MediaSubtitleDto save(MediaSubtitleDto mediaSubtitleD);

    MediaSubtitleDto findMediaSubtitleByContentIdAndLanguageId(long contentId, long languageId);

    List<LanguageDto> findAllLanguageSubtitleByContentId(long contentId);
}
