package ru.araok.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.araok.domain.MediaSubtitle;
import ru.araok.domain.Subtitle;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MediaSubtitleDto {
    private Long id;

    private Long contentId;

    private LanguageDto language;

    private List<SubtitleDto> subtitles;

    public static MediaSubtitle toDomainObject(MediaSubtitleDto dto) {
        List<Subtitle> subtitles = dto.getSubtitles().stream()
                .map(SubtitleDto::toDomainObject)
                .toList();

        return MediaSubtitle.builder()
                .id(dto.id)
                .contentId(dto.contentId)
                .language(
                        LanguageDto.toDomainObject(dto.language)
                )
                .subtitles(subtitles)
                .build();
    }

    public static MediaSubtitleDto toDto(MediaSubtitle mediaSubtitle) {
        List<SubtitleDto> subtitles = mediaSubtitle.getSubtitles().stream()
                .map(SubtitleDto::toDto)
                .toList();

        return MediaSubtitleDto.builder()
                .id(mediaSubtitle.getId())
                .contentId(mediaSubtitle.getContentId())
                .language(
                        LanguageDto.toDto(mediaSubtitle.getLanguage())
                )
                .subtitles(subtitles)
                .build();
    }
}
