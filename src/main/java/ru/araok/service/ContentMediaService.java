package ru.araok.service;

import ru.araok.dto.ContentMediaDto;
import ru.araok.dto.ContentMediaIdDto;

public interface ContentMediaService {
    ContentMediaDto findContentMediaById(ContentMediaIdDto contentMediaIdDto);

    byte[] findMediaByContentMediaId(ContentMediaIdDto contentMediaId);

    ContentMediaDto save(ContentMediaDto contentMediaDto);
}
