package ru.araok.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.araok.dto.AgeLimitDto;
import ru.araok.dto.ContentDto;
import ru.araok.dto.ContentMediaDto;
import ru.araok.dto.ContentMediaIdDto;
import ru.araok.dto.LanguageDto;
import ru.araok.dto.MediaTypeDto;
import ru.araok.dto.UserDto;
import ru.araok.enums.RoleEnum;
import ru.araok.service.impl.MediaServiceImpl;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MediaServiceTest {
    @Mock
    private ContentMediaService contentMediaService;

    @Mock
    private ContentService contentService;

    @Mock
    private MediaTypeService mediaTypeService;

    private MediaService mediaService;

    private byte[] video = {
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
            1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1
    };

    private ContentMediaIdDto contentMediaId;

    private ContentMediaDto contentMedia;

    @BeforeEach
    public void setUp() {
        mediaService = new MediaServiceImpl(
                contentMediaService,
                contentService,
                mediaTypeService
        );

        AgeLimitDto limit = AgeLimitDto.builder()
                .id(1L)
                .description("for children under 6 years of age")
                .limit(0L)
                .build();

        UserDto user = UserDto.builder()
                .id(1L)
                .name("Maxim")
                .phone("89993338951")
                .password("12345")
                .birthDate(LocalDate.of(1994, 8, 5))
                .role(RoleEnum.USER)
                .build();

        LanguageDto language = LanguageDto.builder()
                .id(1L)
                .code2("RU")
                .language("Russian")
                .build();

        ContentDto content = ContentDto.builder()
                .id(1L)
                .name("Unknown Content")
                .limit(limit)
                .artist("Unknown Artist")
                .user(user)
                .createDate(LocalDate.now())
                .language(language)
                .build();

        MediaTypeDto mediaType = MediaTypeDto.builder()
                .id(1L)
                .type("VIDEO")
                .build();

        contentMediaId = ContentMediaIdDto.builder()
                .mediaType(mediaType)
                .content(content)
                .build();

        contentMedia = ContentMediaDto.builder()
                .contentMediaId(contentMediaId)
                .media(video)
                .build();
    }

    @Test
    public void shouldCorrectReturnMediaByContentIdAndTypeId() {
        given(contentService.findContentById(eq(contentMediaId.getContent().getId())))
                .willReturn(contentMediaId.getContent());
        given(mediaTypeService.findById(eq(contentMediaId.getMediaType().getId())))
                .willReturn(contentMediaId.getMediaType());
        given(contentMediaService.findMediaByContentMediaId(any(ContentMediaIdDto.class)))
                .willReturn(video);

        byte[] results = mediaService.findMediaByContentIdAndTypeId(contentMediaId.getContent().getId(), contentMediaId.getMediaType().getId());


        verify(contentService, times(1)).findContentById(eq(contentMediaId.getContent().getId()));
        verify(mediaTypeService, times(1)).findById(eq(contentMediaId.getMediaType().getId()));
        verify(contentMediaService, times(1)).findMediaByContentMediaId(any(ContentMediaIdDto.class));

        assertArrayEquals(video, results);
    }

    @Test
    public void shouldCorrectSaveContentMedia() {
        given(contentMediaService.save(any(ContentMediaDto.class)))
                .willReturn(contentMedia);

        mediaService.save(contentMedia);

        verify(contentMediaService, times(1)).save(any(ContentMediaDto.class));
    }
}
