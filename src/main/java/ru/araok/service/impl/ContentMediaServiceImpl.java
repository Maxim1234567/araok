package ru.araok.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.araok.domain.ContentMedia;
import ru.araok.domain.ContentMediaId;
import ru.araok.dto.ContentMediaDto;
import ru.araok.dto.ContentMediaIdDto;
import ru.araok.exception.NotFoundContentException;
import ru.araok.repository.ContentMediaRepository;
import ru.araok.service.ContentMediaService;

@Service
@RequiredArgsConstructor
public class ContentMediaServiceImpl implements ContentMediaService {

    private final ContentMediaRepository contentMediaRepository;

    @Override
    public ContentMediaDto findContentMediaById(ContentMediaIdDto contentMediaIdDto) {
        ContentMediaId contentMediaId = ContentMediaIdDto.toDomainObject(contentMediaIdDto);

        ContentMedia contentMedia = contentMediaRepository.findByContentMediaId(contentMediaId)
                .orElseThrow(NotFoundContentException::new);

        return ContentMediaDto.toDto(contentMedia);
    }

    @Override
    public byte[] findMediaByContentMediaId(ContentMediaIdDto contentMediaId) {
        return contentMediaRepository.findMediaByContentMediaId(
                ContentMediaIdDto.toDomainObject(contentMediaId)
        );
    }

    @Override
    public ContentMediaDto save(ContentMediaDto contentMediaDto) {
        ContentMedia contentMedia = ContentMediaDto.toDomainObject(contentMediaDto);

        return ContentMediaDto.toDto(
                contentMediaRepository.save(contentMedia)
        );
    }
}
