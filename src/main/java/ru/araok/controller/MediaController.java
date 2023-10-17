package ru.araok.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.araok.dto.ContentDto;
import ru.araok.dto.ContentMediaDto;
import ru.araok.dto.ContentMediaIdDto;
import ru.araok.dto.MediaTypeDto;
import ru.araok.service.ContentMediaService;
import ru.araok.service.ContentService;
import ru.araok.service.MediaSubtitleService;
import ru.araok.service.MediaTypeService;

@RestController
@RequiredArgsConstructor
public class MediaController {
    private final ContentMediaService contentMediaService;

    private final ContentService contentService;

    private final MediaTypeService mediaTypeService;

    @GetMapping("/api/media/{contentId}/{typeId}")
    public ResponseEntity<byte[]> getMedia(@PathVariable("contentId") Long contentId, @PathVariable("typeId") Long typeId) {
        ContentDto content = contentService.findContentById(contentId);
        MediaTypeDto mediaType = mediaTypeService.findById(typeId);

        ContentMediaIdDto contentMediaId = ContentMediaIdDto.builder()
                .mediaType(mediaType)
                .content(content)
                .build();

        return ResponseEntity.ok(
                contentMediaService.findMediaByContentMediaId(
                        contentMediaId
                )
        );
    }

    @PostMapping("/api/media")
    public ResponseEntity<ContentMediaDto> save(@RequestBody ContentMediaDto contentMedia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                contentMediaService.save(contentMedia)
        );
    }
}
