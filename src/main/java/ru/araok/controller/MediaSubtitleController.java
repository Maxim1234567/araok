package ru.araok.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.araok.dto.MediaSubtitleDto;
import ru.araok.service.MediaSubtitleService;

@RestController
@RequiredArgsConstructor
public class MediaSubtitleController {
    private final MediaSubtitleService mediaSubtitleService;

    @GetMapping("/api/subtitle/{contentId}/{languageId}")
    public ResponseEntity<MediaSubtitleDto> getSubtitle(@PathVariable("contentId") Long contentId, @PathVariable("languageId") Long languageId) {
        return ResponseEntity.ok(
                mediaSubtitleService.findMediaSubtitleByContentIdAndLanguageId(
                        contentId,
                        languageId
                )
        );
    }

    @PostMapping("/api/subtitle}")
    public ResponseEntity<MediaSubtitleDto> save(@RequestBody MediaSubtitleDto mediaSubtitle) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                mediaSubtitleService.save(mediaSubtitle)
        );
    }
}
