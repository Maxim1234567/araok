package ru.araok.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.araok.domain.AgeLimit;
import ru.araok.domain.Content;
import ru.araok.domain.ContentCounter;
import ru.araok.domain.Language;
import ru.araok.domain.User;
import ru.araok.dto.ContentDto;
import ru.araok.property.ApplicationProperties;
import ru.araok.repository.ContentCounterRepository;
import ru.araok.repository.ContentRecommendedRepository;
import ru.araok.repository.ContentRepository;
import ru.araok.service.impl.ContentServiceImpl;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static ru.araok.Utils.assertEqualsContentDto;

@ExtendWith(MockitoExtension.class)
public class ContentServiceTest {
    @Mock
    private ContentRepository contentRepository;

    @Mock
    private ContentCounterRepository contentCounterRepository;

    @Mock
    private ContentRecommendedRepository contentRecommendedRepository;

    private ContentService contentService;

    private Content content1;

    private Content content2;

    private Content content3;

    @BeforeEach
    public void setUp() {
        contentService = new ContentServiceImpl(
                new ApplicationProperties(1000L),
                contentRepository,
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
    }

    @Test
    public void shouldCorrectReturnAllContent() {
        List<Content> contents = List.of(
                content1, content2, content3
        );

        given(contentRepository.findAll())
                .willReturn(contents);

        List<ContentDto> results = contentService.getAll();

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

        List<ContentDto> results = contentService.getNewContents();

        assertThat(results).isNotNull()
                .hasSize(contents.size());

        for(int i = 0; i < contents.size(); i++) {
            assertEqualsContentDto(contents.get(i), results.get(i));
        }
    }
}
