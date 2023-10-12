package ru.araok.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.araok.domain.Language;
import ru.araok.domain.MediaSubtitle;
import ru.araok.domain.Subtitle;
import ru.araok.dto.MediaSubtitleDto;
import ru.araok.dto.SubtitleDto;
import ru.araok.exception.NotFoundContentException;
import ru.araok.repository.MediaSubtitleRepository;
import ru.araok.service.impl.MediaSubtitleServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MediaSubtitleServiceTest {

    @Mock
    private MediaSubtitleRepository mediaSubtitleRepository;

    private MediaSubtitleService mediaSubtitleService;

    private MediaSubtitle mediaSubtitle;

    @BeforeEach
    public void setUp() {
        mediaSubtitleService = new MediaSubtitleServiceImpl(mediaSubtitleRepository);

        Subtitle subtitle1 = Subtitle.builder()
                .id(1L)
                .line("line1")
                .to(1L)
                .from(2L)
                .build();

        Subtitle subtitle2 = Subtitle.builder()
                .id(2L)
                .line("line2")
                .to(2L)
                .from(3L)
                .build();

        Subtitle subtitle3 = Subtitle.builder()
                .id(3L)
                .line("line3")
                .to(3L)
                .from(4L)
                .build();

        Language language = Language.builder()
                .id(1L)
                .language("Russian")
                .code2("RU")
                .build();

        mediaSubtitle = MediaSubtitle.builder()
                .id(1L)
                .contentId(1L)
                .language(language)
                .subtitles(
                        List.of(subtitle1, subtitle2, subtitle3)
                )
                .build();
    }

    @Test
    public void shouldCorrectReturnMediaSubtitleByContentIdAndLanguageId() {
        given(mediaSubtitleRepository.findByContentIdAndLanguageId(
                eq(mediaSubtitle.getContentId()),
                eq(mediaSubtitle.getLanguage().getId()))
        ).willReturn(Optional.of(mediaSubtitle));

        MediaSubtitleDto mediaSubtitleDto = mediaSubtitleService.findMediaSubtitleByContentIdAndLanguageId(mediaSubtitle.getContentId(), mediaSubtitle.getLanguage().getId());

        verify(mediaSubtitleRepository, times(1)).findByContentIdAndLanguageId(eq(mediaSubtitle.getContentId()), eq(mediaSubtitle.getLanguage().getId()));

        assertThat(mediaSubtitleDto).isNotNull()
                .matches(ms -> ms.getId().equals(mediaSubtitle.getId()))
                .matches(ms -> ms.getContentId().equals(mediaSubtitle.getContentId()));

        assertThat(mediaSubtitleDto.getLanguage()).isNotNull()
                .matches(l -> l.getId().equals(mediaSubtitle.getLanguage().getId()))
                .matches(l -> l.getCode2().equals(mediaSubtitle.getLanguage().getCode2()))
                .matches(l -> l.getLanguage().equals(mediaSubtitle.getLanguage().getLanguage()));

        assertEqualsSubtitleList(mediaSubtitle.getSubtitles(), mediaSubtitleDto.getSubtitles());
    }

    @Test
    public void shouldDoesThrowNotFoundContentException() {
        given(mediaSubtitleRepository.findByContentIdAndLanguageId(
                eq(mediaSubtitle.getContentId()),
                eq(mediaSubtitle.getLanguage().getId())
        )).willReturn(Optional.empty());

        assertThrows(NotFoundContentException.class, () -> mediaSubtitleService.findMediaSubtitleByContentIdAndLanguageId(mediaSubtitle.getContentId(), mediaSubtitle.getLanguage().getId()));
    }

    private void assertEqualsSubtitleList(List<Subtitle> excepted, List<SubtitleDto> result) {
        assertThat(result).isNotNull()
                .hasSize(excepted.size());

        for(int i = 0; i < excepted.size(); i++) {
            assertEqualsSubtitle(excepted.get(i), result.get(i));
        }
    }

    private void assertEqualsSubtitle(Subtitle excepted, SubtitleDto result) {
        assertThat(result).isNotNull()
                .matches(s -> s.getId().equals(excepted.getId()))
                .matches(s -> s.getLine().equals(excepted.getLine()))
                .matches(s -> s.getFrom().equals(excepted.getFrom()))
                .matches(s -> s.getTo().equals(excepted.getTo()));
    }
}
