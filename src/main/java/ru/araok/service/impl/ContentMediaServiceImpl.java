package ru.araok.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.araok.domain.ContentMedia;
import ru.araok.domain.ContentMediaId;
import ru.araok.dto.ContentMediaDto;
import ru.araok.dto.ContentMediaIdDto;
import ru.araok.exception.NotFoundContentException;
import ru.araok.repository.ContentMediaRepository;
import ru.araok.service.ContentMediaService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContentMediaServiceImpl implements ContentMediaService {

    private final ContentMediaRepository contentMediaRepository;

    @Override
    @Transactional(readOnly = true)
    public ContentMediaDto findContentMediaById(ContentMediaIdDto contentMediaIdDto) {
        log.info("find content media by id");

        ContentMediaId contentMediaId = ContentMediaIdDto.toDomainObject(contentMediaIdDto);

        ContentMedia contentMedia = contentMediaRepository.findByContentMediaId(contentMediaId)
                .orElseThrow(NotFoundContentException::new);

        return ContentMediaDto.toDto(contentMedia);
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] findMediaByContentMediaId(ContentMediaIdDto contentMediaId) {
        log.info("find media by content media id");

        return contentMediaRepository.findMediaByContentMediaId(
                ContentMediaIdDto.toDomainObject(contentMediaId)
        );
    }

    @Override
    @Transactional
    public ContentMediaDto save(ContentMediaDto contentMediaDto) {
        log.info("save content media");

        ContentMedia contentMedia = ContentMediaDto.toDomainObject(contentMediaDto);

        return ContentMediaDto.toDto(
                contentMediaRepository.save(contentMedia)
        );
    }
}
