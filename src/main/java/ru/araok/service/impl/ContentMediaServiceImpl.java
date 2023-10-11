package ru.araok.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.araok.domain.ContentMedia;
import ru.araok.domain.ContentMediaId;
import ru.araok.dto.ContentMediaDto;
import ru.araok.dto.ContentMediaIdDto;
import ru.araok.repository.ContentMediaRepository;
import ru.araok.service.ContentMediaService;

@Service
@RequiredArgsConstructor
public class ContentMediaServiceImpl implements ContentMediaService {

    private final ContentMediaRepository contentMediaRepository;

    @Override
    public ContentMediaDto findContentMediaById(ContentMediaIdDto contentMediaIdDto) {
        ContentMediaId contentMediaId = ContentMediaIdDto.toDomainObject(contentMediaIdDto);

        System.out.println("contentMediaRepository.findByContentMediaId 1");

        ContentMedia contentMedia = contentMediaRepository.findByContentMediaId(contentMediaId);

        System.out.println("contentMediaRepository.findByContentMediaId 2");

        return ContentMediaDto.toDto(contentMedia);
    }

    @Override
    public ContentMediaDto save(ContentMediaDto contentMediaDto) {
        ContentMedia contentMedia = ContentMediaDto.toDomainObject(contentMediaDto);

        return ContentMediaDto.toDto(
                contentMediaRepository.save(contentMedia)
        );
    }
}
