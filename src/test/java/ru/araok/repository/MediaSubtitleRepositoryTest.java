package ru.araok.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.araok.domain.Language;
import ru.araok.domain.MediaSubtitle;
import ru.araok.domain.Subtitle;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MediaSubtitleRepositoryTest {

    private static final Long CONTENT_ID = 1L;

    @Autowired
    private MediaSubtitleRepository mediaSubtitleRepository;

    private MediaSubtitle ruSubtitle;

    private MediaSubtitle enSubtitle;

    @BeforeEach
    public void setUp() {
//-------------------------------------ru----------------------------------------

        Subtitle ruSubtitle1 = Subtitle.builder()
                .line("line1")
                .to(1L)
                .from(2L)
                .build();

        Subtitle ruSubtitle2 = Subtitle.builder()
                .line("line2")
                .to(2L)
                .from(3L)
                .build();

        Subtitle ruSubtitle3 = Subtitle.builder()
                .line("line2")
                .to(3L)
                .from(4L)
                .build();

        Language ruLanguage = Language.builder()
                .id(1L)
                .language("Russian")
                .code2("RU")
                .build();

        ruSubtitle = MediaSubtitle.builder()
                .contentId(CONTENT_ID)
                .language(ruLanguage)
                .subtitles(
                        List.of(ruSubtitle1, ruSubtitle2, ruSubtitle3)
                )
                .build();

//-------------------------------------en----------------------------------------

        Subtitle enSubtitle1 = Subtitle.builder()
                .line("line4")
                .to(1L)
                .from(2L)
                .build();

        Subtitle enSubtitle2 = Subtitle.builder()
                .line("line5")
                .to(2L)
                .from(3L)
                .build();

        Subtitle enSubtitle3 = Subtitle.builder()
                .line("line6")
                .to(3L)
                .from(4L)
                .build();

        Language enLanguage = Language.builder()
                .id(2L)
                .language("English")
                .code2("EN")
                .build();

        enSubtitle = MediaSubtitle.builder()
                .contentId(CONTENT_ID)
                .language(enLanguage)
                .subtitles(
                        List.of(enSubtitle1, enSubtitle2, enSubtitle3)
                )
                .build();
    }

    @Test
    public void shouldCorrectSaveMediaSubtitle() {
        MediaSubtitle mediaSubtitle = mediaSubtitleRepository.save(ruSubtitle);

        MediaSubtitle result = mediaSubtitleRepository.findById(mediaSubtitle.getId()).get();

        assertThatMediaSubtitle(ruSubtitle, result);
    }

    @Test
    public void shouldCorrectReturnMediaSubtitleByMediaSubtitleId() {
        mediaSubtitleRepository.save(ruSubtitle);
        MediaSubtitle enMediaSubtitle = mediaSubtitleRepository.save(enSubtitle);


        MediaSubtitle result = mediaSubtitleRepository.findById(enMediaSubtitle.getId()).get();

        assertThatMediaSubtitle(enSubtitle, result);
    }

    @Test
    public void shouldCorrectReturnMediaSubtitleByContentId() {
        mediaSubtitleRepository.save(ruSubtitle);
        mediaSubtitleRepository.save(enSubtitle);

        List<MediaSubtitle> mediaSubtitles = mediaSubtitleRepository.findByContentId(CONTENT_ID);

        assertThat(mediaSubtitles).isNotNull().hasSize(2);

        MediaSubtitle ruMediaSubtitle = mediaSubtitles.stream()
                .filter(m -> m.getLanguage().getId().equals(1L))
                .findFirst()
                .get();

        MediaSubtitle enMediaSubtitle = mediaSubtitles.stream()
                .filter(m -> m.getLanguage().getId().equals(2L))
                .findFirst()
                .get();

        assertThatMediaSubtitle(ruSubtitle, ruMediaSubtitle);
        assertThatMediaSubtitle(enSubtitle, enMediaSubtitle);
    }

    @Test
    public void shouldCorrectReturnMediaSubtitleByLanguageId() {
        mediaSubtitleRepository.save(ruSubtitle);
        mediaSubtitleRepository.save(enSubtitle);

        MediaSubtitle result = mediaSubtitleRepository.findByLanguageId(
                ruSubtitle.getLanguage().getId()
        ).get(0);

        assertThatMediaSubtitle(ruSubtitle, result);
    }

    @Test
    public void shouldCorrectReturnMediaSubtitleByContentIdAndLanguageId() {
        mediaSubtitleRepository.save(ruSubtitle);
        mediaSubtitleRepository.save(enSubtitle);

        MediaSubtitle result = mediaSubtitleRepository.findByContentIdAndLanguageId(
                ruSubtitle.getContentId(), ruSubtitle.getLanguage().getId()
        ).get();

        assertThatMediaSubtitle(ruSubtitle, result);
    }

    private void assertThatMediaSubtitle(MediaSubtitle expected, MediaSubtitle result) {
        Language language = result.getLanguage();
        List<Subtitle> subtitles = result.getSubtitles();

        assertThat(language).isNotNull()
                .matches(l -> l.getId().equals(expected.getLanguage().getId()))
                .matches(l -> l.getCode2().equals(expected.getLanguage().getCode2()))
                .matches(l -> l.getLanguage().equals(expected.getLanguage().getLanguage()));

        assertThatSubtitleList(expected.getSubtitles(), subtitles);

        assertThat(result).isNotNull();
    }

    private void assertThatSubtitleList(List<Subtitle> expected, List<Subtitle> result) {
        assertThat(result).isNotNull()
                .hasSize(expected.size());

        for(int i = 0; i < expected.size(); i++) {
            assertThatSubtitle(expected.get(i), result.get(i));
        }
    }

    private void assertThatSubtitle(Subtitle expected, Subtitle result) {
        assertThat(result).isNotNull()
                .matches(s -> s.getId().equals(expected.getId()))
                .matches(s -> s.getLine().equals(expected.getLine()))
                .matches(s -> s.getTo().equals(expected.getTo()))
                .matches(s -> s.getFrom().equals(expected.getFrom()));
    }
}
