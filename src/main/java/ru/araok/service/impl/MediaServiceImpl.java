package ru.araok.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.araok.dto.ContentDto;
import ru.araok.dto.ContentMediaDto;
import ru.araok.dto.ContentMediaIdDto;
import ru.araok.dto.MediaTypeDto;
import ru.araok.service.ContentMediaService;
import ru.araok.service.ContentService;
import ru.araok.service.MediaService;
import ru.araok.service.MediaTypeService;

@Slf4j
@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
    private final ContentMediaService contentMediaService;

    private final ContentService contentService;

    private final MediaTypeService mediaTypeService;

    @Override
    @Transactional(readOnly = true)
    public byte[] findMediaByContentIdAndTypeId(Long contentId, Long typeId) {
        log.info("find media by content id: {} and type id: {}", contentId, typeId);

        ContentDto content = contentService.findContentById(contentId);
        MediaTypeDto mediaType = mediaTypeService.findById(typeId);

        ContentMediaIdDto contentMediaId = ContentMediaIdDto.builder()
                .mediaType(mediaType)
                .content(content)
                .build();

        return contentMediaService.findMediaByContentMediaId(contentMediaId);
    }

    @Override
    @Transactional
    public ContentMediaDto save(ContentMediaDto contentMediaDto) {
        log.info("save content media");

        return contentMediaService.save(contentMediaDto);
    }
}
