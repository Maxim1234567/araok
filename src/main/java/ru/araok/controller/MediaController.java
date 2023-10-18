package ru.araok.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.araok.dto.ContentMediaDto;
import ru.araok.service.MediaService;

@RestController
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;

    @GetMapping("/api/media/{contentId}/{typeId}")
    public ResponseEntity<byte[]> getMedia(@PathVariable("contentId") Long contentId, @PathVariable("typeId") Long typeId) {
        return ResponseEntity.ok(
                mediaService.findMediaByContentIdAndTypeId(contentId, typeId)
        );
    }

    @PostMapping("/api/media")
    public ResponseEntity<ContentMediaDto> save(@RequestBody ContentMediaDto contentMedia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                mediaService.save(contentMedia)
        );
    }
}
