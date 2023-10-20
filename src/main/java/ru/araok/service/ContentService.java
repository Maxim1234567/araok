package ru.araok.service;

import ru.araok.constant.TypeContent;
import ru.araok.dto.ContentDto;
import ru.araok.dto.ContentWithContentMediaAndMediaSubtitleDto;

import java.util.List;

public interface ContentService {

    List<ContentDto> findContentsByType(TypeContent type);

    List<ContentDto> findContentsByName(String name);

    ContentDto findContentById(Long id);

    ContentDto save(ContentWithContentMediaAndMediaSubtitleDto content);
}
