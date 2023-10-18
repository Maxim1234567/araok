package ru.araok.service;

import ru.araok.dto.ContentDto;
import ru.araok.dto.ContentWithContentMediaAndMediaSubtitleDto;

import java.util.List;

public interface ContentService {
    List<ContentDto> getAll();

    List<ContentDto> getNewContents();

    List<ContentDto> getPopularContents();

    List<ContentDto> findContentsByName(String name);

    ContentDto findContentById(Long id);

    ContentDto save(ContentWithContentMediaAndMediaSubtitleDto content);

    List<ContentDto> getRecommendedContents();
}
