package ru.araok.service;

import ru.araok.dto.ContentMediaDto;

public interface MediaService {
    byte[] findMediaByContentIdAndTypeId(Long contentId, Long typeId);

    ContentMediaDto save(ContentMediaDto contentMediaDto);
}
