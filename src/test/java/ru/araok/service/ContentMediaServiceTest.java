package ru.araok.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.araok.domain.AgeLimit;
import ru.araok.domain.Content;
import ru.araok.domain.ContentMedia;
import ru.araok.domain.ContentMediaId;
import ru.araok.domain.Language;
import ru.araok.domain.MediaType;
import ru.araok.domain.User;
import ru.araok.dto.ContentMediaDto;
import ru.araok.dto.ContentMediaIdDto;
import ru.araok.repository.ContentMediaRepository;
import ru.araok.service.impl.ContentMediaServiceImpl;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ru.araok.Utils.assertEqualsContent;
import static ru.araok.Utils.assertEqualsContentDto;

@ExtendWith(MockitoExtension.class)
public class ContentMediaServiceTest {

    @Mock
    private ContentMediaRepository contentMediaRepository;

    private ContentMediaService contentMediaService;

    private ContentMedia contentMedia;

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

    @BeforeEach
    public void setUp() {
        AgeLimit limit = AgeLimit.builder()
                .id(1L)
                .description("for children under 6 years of age")
                .limit(0L)
                .build();

        User user = User.builder()
                .id(1L)
                .name("Maxim")
                .phone("89993338951")
                .password("12345")
                .birthDate(LocalDate.of(1994, 8, 5))
                .role("USER")
                .build();

        Language language = Language.builder()
                .id(1L)
                .code2("RU")
                .language("Russian")
                .build();

        Content content = Content.builder()
                .id(1L)
                .name("Unknown Content")
                .limit(limit)
                .artist("Unknown Artist")
                .user(user)
                .createDate(LocalDate.now())
                .language(language)
                .build();

        contentMediaService = new ContentMediaServiceImpl(contentMediaRepository);

        MediaType mediaType = MediaType.builder()
                .id(1L)
                .type("VIDEO")
                .build();

        ContentMediaId contentMediaId = ContentMediaId.builder()
                .content(content)
                .mediaType(mediaType)
                .build();

        contentMedia = ContentMedia.builder()
                .contentMediaId(contentMediaId)
                .media(video)
                .build();
    }

    @Test
    public void shouldCorrectReturnContentMediaByContentMediaId() {
        ContentMediaIdDto contentMediaIdDto = ContentMediaIdDto.toDto(
                contentMedia.getContentMediaId()
        );

        given(contentMediaRepository.findByContentMediaId(any(ContentMediaId.class)))
                .willReturn(contentMedia);

        ContentMediaDto result = contentMediaService.findContentMediaById(contentMediaIdDto);

        verify(contentMediaRepository, times(1))
                .findByContentMediaId(any(ContentMediaId.class));

        assertEqualsContentMedia(result);
    }

    @Test
    public void shouldCorrectSaveContentMedia() {
        ContentMediaDto contentMediaDto = ContentMediaDto.toDto(
                contentMedia
        );

        given(contentMediaRepository.save(any(ContentMedia.class)))
                .willReturn(contentMedia);

        ContentMediaDto result = contentMediaService
                .save(contentMediaDto);

        verify(contentMediaRepository, times(1))
                .save(any(ContentMedia.class));

        assertEqualsContentMedia(result);
    }

    @Test
    public void shouldCorrectReturnMediaByContentMediaId() {
        ContentMediaIdDto contentMediaId = ContentMediaIdDto.toDto(
                contentMedia.getContentMediaId()
        );

        given(contentMediaRepository.findMediaByContentMediaId(any(ContentMediaId.class)))
                .willReturn(contentMedia.getMedia());

        byte[] media = contentMediaService
                .findMediaByContentMediaId(contentMediaId);

        verify(contentMediaRepository, times(1))
                .findMediaByContentMediaId(any(ContentMediaId.class));

        assertArrayEquals(media, contentMedia.getMedia());
    }

    private void assertEqualsContentMedia(ContentMediaDto result) {
        ContentMediaId contentMediaId = contentMedia.getContentMediaId();
        MediaType mediaType = contentMediaId.getMediaType();

        assertThat(result.getContentMediaId().getMediaType()).isNotNull()
                .matches(m -> m.getId().equals(mediaType.getId()))
                .matches(m -> m.getType().equals(mediaType.getType()));

        assertEqualsContentDto(contentMediaId.getContent(), result.getContentMediaId().getContent());

        assertArrayEquals(result.getMedia(), contentMedia.getMedia());
    }
}
