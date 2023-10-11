package ru.araok.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.araok.domain.Subtitle;
import ru.araok.repository.MediaSubtitleRepository;
import ru.araok.service.impl.MediaSubtitleServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MediaSubtitleServiceTest {

    @Mock
    private MediaSubtitleRepository mediaSubtitleRepository;

    private MediaSubtitleService mediaSubtitleService;

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
    }
}
