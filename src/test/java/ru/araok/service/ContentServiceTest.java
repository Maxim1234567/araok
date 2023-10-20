package ru.araok.service;

import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.araok.constant.TypeContent;
import ru.araok.domain.AgeLimit;
import ru.araok.domain.Content;
import ru.araok.domain.ContentCounter;
import ru.araok.domain.ContentMedia;
import ru.araok.domain.ContentMediaId;
import ru.araok.domain.ContentRecommended;
import ru.araok.domain.Language;
import ru.araok.domain.MediaSubtitle;
import ru.araok.domain.MediaType;
import ru.araok.domain.Subtitle;
import ru.araok.domain.User;
import ru.araok.dto.ContentDto;
import ru.araok.dto.ContentMediaDto;
import ru.araok.dto.ContentWithContentMediaAndMediaSubtitleDto;
import ru.araok.dto.MediaSubtitleDto;
import ru.araok.exception.NotFoundContentException;
import ru.araok.property.ApplicationProperties;
import ru.araok.repository.ContentCounterRepository;
import ru.araok.repository.ContentMediaRepository;
import ru.araok.repository.ContentRecommendedRepository;
import ru.araok.repository.ContentRepository;
import ru.araok.repository.MediaSubtitleRepository;
import ru.araok.service.impl.ContentServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ru.araok.Utils.assertEqualsContentDto;

@ExtendWith(MockitoExtension.class)
public class ContentServiceTest {
    @Mock
    private ContentRepository contentRepository;

    @Mock
    private ContentMediaRepository contentMediaRepository;

    @Mock
    private MediaSubtitleRepository mediaSubtitleRepository;

    @Mock
    private ContentCounterRepository contentCounterRepository;

    @Mock
    private ContentRecommendedRepository contentRecommendedRepository;

    private ContentService contentService;

    private Content content1;

    private Content content2;

    private Content content3;

    private ContentCounter contentCounter1;

    private ContentCounter contentCounter2;

    private ContentCounter contentCounter3;

    private ContentRecommended contentRecommended1;

    private ContentRecommended contentRecommended2;

    private ContentRecommended contentRecommended3;

    private ContentMedia contentMedia;

    private MediaSubtitle mediaSubtitle;

    private ContentWithContentMediaAndMediaSubtitleDto saveContent;

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
        contentService = new ContentServiceImpl(
                new ApplicationProperties(1000L),
                contentRepository,
                contentMediaRepository,
                mediaSubtitleRepository,
                contentCounterRepository,
                contentRecommendedRepository
        );

        AgeLimit limit1 = AgeLimit.builder()
                .id(1L)
                .description("for children under 6 years of age")
                .limit(0L)
                .build();

        AgeLimit limit2 = AgeLimit.builder()
                .id(5L)
                .description("prohibited for children")
                .limit(18L)
                .build();

        AgeLimit limit3 = AgeLimit.builder()
                .id(3L)
                .description("for children over 12 years of age")
                .limit(12L)
                .build();

        Language language1 = Language.builder()
                .id(1L)
                .code2("RU")
                .language("Russian")
                .build();

        Language language2 = Language.builder()
                .id(2L)
                .code2("EN")
                .language("English")
                .build();

        Language language3 = Language.builder()
                .id(3L)
                .code2("DE")
                .language("German")
                .build();

        User user = User.builder()
                .id(1L)
                .name("Maxim")
                .phone("89993338951")
                .password("12345")
                .birthDate(LocalDate.of(1994, 8, 5))
                .role("USER")
                .build();

        content1 = Content.builder()
                .id(1L)
                .name("Unknown Content")
                .limit(limit1)
                .artist("Unknown Artist")
                .user(user)
                .createDate(LocalDate.now())
                .language(language1)
                .build();

        content2 = Content.builder()
                .id(2L)
                .name("Unknown Content 2")
                .limit(limit2)
                .artist("Unknown Artist 2")
                .user(user)
                .createDate(LocalDate.now().plusDays(8))
                .language(language2)
                .build();

        content3 = Content.builder()
                .id(3L)
                .name("Unknown Content 3")
                .limit(limit3)
                .artist("Unknown Artist 3")
                .user(user)
                .createDate(LocalDate.now().plusDays(12))
                .language(language3)
                .build();

        contentCounter1 = ContentCounter.builder()
                .id(1L)
                .content(content1)
                .user(user)
                .count(1001L)
                .build();

        contentCounter2 = ContentCounter.builder()
                .id(2L)
                .content(content2)
                .user(user)
                .count(1001L)
                .build();

        contentCounter3 = ContentCounter.builder()
                .id(3L)
                .content(content3)
                .user(user)
                .count(1001L)
                .build();

        contentRecommended1 = ContentRecommended.builder()
                .id(1L)
                .content(content1)
                .build();

        contentRecommended2 = ContentRecommended.builder()
                .id(2L)
                .content(content2)
                .build();

        contentRecommended3 = ContentRecommended.builder()
                .id(3L)
                .content(content3)
                .build();

        MediaType mediaType = MediaType.builder()
                .id(1L)
                .type("VIDEO")
                .build();

        ContentMediaId contentMediaId = ContentMediaId.builder()
                .content(content1)
                .mediaType(mediaType)
                .build();

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

        mediaSubtitle = MediaSubtitle.builder()
                .id(1L)
                .content(content1)
                .language(language1)
                .subtitles(
                        List.of(subtitle1, subtitle2, subtitle3)
                )
                .build();

        contentMedia = ContentMedia.builder()
                .contentMediaId(contentMediaId)
                .media(video)
                .build();

        saveContent = ContentWithContentMediaAndMediaSubtitleDto.builder()
                .content(ContentDto.toDto(content1))
                .preview(ContentMediaDto.toDto(contentMedia))
                .video(ContentMediaDto.toDto(contentMedia))
                .mediaSubtitle(MediaSubtitleDto.toDto(mediaSubtitle))
                .build();
    }

    @Test
    public void shouldCorrectReturnAllContent() {
        List<Content> contents = List.of(
                content1, content2, content3
        );

        given(contentRepository.findAll())
                .willReturn(contents);

        List<ContentDto> results = contentService.findContentsByType(TypeContent.ALL);

        verify(contentRepository, times(1)).findAll();

        assertThat(results).isNotNull()
                .hasSize(contents.size());

        for(int i = 0; i < contents.size(); i++) {
            assertEqualsContentDto(contents.get(i), results.get(i));
        }
    }

    @Test
    public void shouldCorrectReturnNewContents() {
        List<Content> contents = List.of(
                content1, content2, content3
        );

        given(contentRepository.findByCreateDateLessThanNow())
                .willReturn(contents);

        List<ContentDto> results = contentService.findContentsByType(TypeContent.NEW);

        verify(contentRepository, times(1)).findByCreateDateLessThanNow();

        assertThat(results).isNotNull()
                .hasSize(contents.size());

        for(int i = 0; i < contents.size(); i++) {
            assertEqualsContentDto(contents.get(i), results.get(i));
        }
    }

    @Test
    public void shouldCorrectReturnPopularContents() {
        List<ContentCounter> contentCounters = List.of(
                contentCounter1, contentCounter2, contentCounter3
        );

        given(contentCounterRepository.findByCountGreaterThan(eq(1000L)))
                .willReturn(contentCounters);

        List<ContentDto> results = contentService.findContentsByType(TypeContent.POPULAR);

        verify(contentCounterRepository, times(1)).findByCountGreaterThan(eq(1000L));

        assertThat(results).isNotNull()
                .hasSize(contentCounters.size());

        for(int i = 0; i < contentCounters.size(); i++) {
            assertEqualsContentDto(contentCounters.get(i).getContent(), results.get(i));
        }
    }

    @Test
    public void shouldCorrectReturnContentFindByName() {
        List<Content> contents = List.of(
                content1, content2, content3
        );

        given(contentRepository.findByNameContainingIgnoreCase(eq("unknown")))
                .willReturn(contents);

        List<ContentDto> results = contentService.findContentsByName("unknown");

        verify(contentRepository, times(1)).findByNameContainingIgnoreCase(eq("unknown"));

        assertThat(results).isNotNull()
                .hasSize(contents.size());

        for(int i = 0; i < contents.size(); i++) {
            assertEqualsContentDto(contents.get(i), results.get(i));
        }
    }

    @Test
    public void shouldCorrectReturnContentById() {
        given(contentRepository.findById(eq(content1.getId())))
                .willReturn(Optional.of(content1));

        ContentDto result = contentService.findContentById(content1.getId());

        verify(contentRepository, times(1)).findById(eq(content1.getId()));

        assertEqualsContentDto(content1, result);
    }

    @Test
    public void shouldDoesThrowsNotFoundContentExceptionFindContentById() {
        given(contentRepository.findById(eq(content1.getId())))
                .willReturn(Optional.empty());

        assertThrows(NotFoundContentException.class, () -> contentService.findContentById(content1.getId()));

        verify(contentRepository, times(1)).findById(eq(content1.getId()));
    }

    @Test
    public void shouldCorrectSaveContent() {
        given(contentRepository.save(any(Content.class)))
                .willReturn(content1);

        ContentDto result = contentService.save(
                saveContent
        );

        verify(contentRepository, times(1)).save(any(Content.class));
        verify(contentMediaRepository, times(2)).save(any(ContentMedia.class));
        verify(mediaSubtitleRepository, times(1)).save(any(MediaSubtitle.class));

        assertEqualsContentDto(content1, result);
    }

    @Test
    public void shouldCorrectReturnContentRecommended() {
        List<ContentRecommended> contentRecommends = List.of(
                contentRecommended1, contentRecommended2, contentRecommended3
        );

        given(contentRecommendedRepository.findAll())
                .willReturn(contentRecommends);

        List<ContentDto> results = contentService.findContentsByType(TypeContent.RECOMMENDED);

        verify(contentRecommendedRepository, times(1)).findAll();

        assertThat(results).isNotNull()
                .hasSize(contentRecommends.size());

        for(int i = 0; i < contentRecommends.size(); i++) {
            assertEqualsContentDto(contentRecommends.get(i).getContent(), results.get(i));
        }
    }
}
