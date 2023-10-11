package ru.araok.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.araok.domain.ContentMediaId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentMediaIdDto {
    private Long contentId;

    private MediaTypeDto mediaType;

    public static ContentMediaId toDomainObject(ContentMediaIdDto dto) {
        return ContentMediaId.builder()
                .contentId(dto.contentId)
                .mediaType(
                        MediaTypeDto.toDomainObject(dto.mediaType)
                )
                .build();
    }

    public static ContentMediaIdDto toDto(ContentMediaId contentMediaId) {
        return ContentMediaIdDto.builder()
                .contentId(contentMediaId.getContentId())
                .mediaType(
                        MediaTypeDto.toDto(contentMediaId.getMediaType())
                )
                .build();
    }
}
