package ru.araok.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.araok.constant.TypeContent;
import ru.araok.dto.ContentDto;
import ru.araok.dto.ContentWithContentMediaAndMediaSubtitleDto;
import ru.araok.service.ContentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;

    @GetMapping("/api/content")
    public ResponseEntity<List<ContentDto>> getContents(@RequestParam("type") TypeContent type) {
        List<ContentDto> contents = new ArrayList<>();

        if(type == TypeContent.ALL) {
            contents = contentService.getAll();
        } else if(type == TypeContent.NEW) {
            contents = contentService.getNewContents();
        } else if(type == TypeContent.POPULAR) {
            contents = contentService.getPopularContents();
        } else if(type == TypeContent.RECOMMENDED) {
            contents = contentService.getRecommendedContents();
        }

        return ResponseEntity.ok(contents);
    }

    @GetMapping("/api/content/{name}")
    public ResponseEntity<List<ContentDto>> getContentsByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(contentService.findContentsByName(name));
    }

    @GetMapping("/api/content/{id}")
    public ResponseEntity<ContentDto> getContentById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                contentService.findContentById(id)
        );
    }

    @PostMapping("/api/content")
    public ResponseEntity<String> save(@RequestBody ContentWithContentMediaAndMediaSubtitleDto content) {
        contentService.save(content);
        return ResponseEntity.ok("OK");
    }
}
