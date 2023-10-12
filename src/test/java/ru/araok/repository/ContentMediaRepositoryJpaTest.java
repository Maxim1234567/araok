package ru.araok.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.araok.domain.ContentMedia;
import ru.araok.domain.ContentMediaId;
import ru.araok.domain.MediaType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ContentMediaRepositoryJpaTest {

    @Autowired
    private ContentMediaRepository contentMediaRepository;

    private byte[] image = {
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
    };

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

    private ContentMedia contentMedia;

    private ContentMedia videoContentMedia;

    @BeforeEach
    public void setUp() {
        MediaType imageType = MediaType.builder()
                .id(3L)
                .type("IMAGE")
                .build();

        ContentMediaId contentMediaId = ContentMediaId.builder()
                .contentId(1L)
                .mediaType(imageType)
                .build();

        contentMedia = ContentMedia.builder()
                .contentMediaId(contentMediaId)
                .media(image)
                .build();

        MediaType videoType = MediaType.builder()
                .id(1L)
                .type("VIDEO")
                .build();

        ContentMediaId videoContentMediaId = ContentMediaId.builder()
                .contentId(2L)
                .mediaType(videoType)
                .build();

        videoContentMedia = ContentMedia.builder()
                .contentMediaId(videoContentMediaId)
                .media(video)
                .build();
    }

    @Test
    public void shouldCorrectlySaveContentMedia() {
        contentMediaRepository.save(contentMedia);

        ContentMediaId primaryKey = ContentMediaId.builder()
                .contentId(contentMedia.getContentMediaId().getContentId())
                .mediaType(contentMedia.getContentMediaId().getMediaType())
                .build();

        ContentMedia result = contentMediaRepository.findByContentMediaId(
                primaryKey
        );

        MediaType mediaType = contentMedia.getContentMediaId().getMediaType();

        assertThat(result).isNotNull()
                .matches(r -> r.getContentMediaId().getContentId().equals(contentMedia.getContentMediaId().getContentId()))
                .matches(r -> r.getContentMediaId().getMediaType().getId().equals(contentMedia.getContentMediaId().getMediaType().getId()));

        assertThat(result.getContentMediaId().getMediaType()).isNotNull()
                .matches(m -> m.getId().equals(mediaType.getId()))
                .matches(m -> m.getType().equals(mediaType.getType()));

        assertArrayEquals(result.getMedia(), contentMedia.getMedia());
    }

    @Test
    public void shouldCorrectlyFindContentMediaByContentMediaId() {
        contentMediaRepository.save(contentMedia);

        ContentMediaId primaryKey = ContentMediaId.builder()
                .contentId(contentMedia.getContentMediaId().getContentId())
                .mediaType(contentMedia.getContentMediaId().getMediaType())
                .build();

        ContentMedia result = contentMediaRepository.findByContentMediaId(
                primaryKey
        );

        MediaType mediaType = contentMedia.getContentMediaId().getMediaType();

        assertThat(result).isNotNull()
                .matches(r -> r.getContentMediaId().getContentId().equals(contentMedia.getContentMediaId().getContentId()))
                .matches(r -> r.getContentMediaId().getMediaType().getId().equals(contentMedia.getContentMediaId().getMediaType().getId()));

        assertThat(result.getContentMediaId().getMediaType()).isNotNull()
                .matches(m -> m.getId().equals(mediaType.getId()))
                .matches(m -> m.getType().equals(mediaType.getType()));

        assertArrayEquals(result.getMedia(), contentMedia.getMedia());
    }

    @Test
    public void shouldCorrectlyFindContentMediaByTypeId() {
        contentMediaRepository.save(contentMedia);
        contentMediaRepository.save(videoContentMedia);

        long typeIdImage = contentMedia.getContentMediaId().getMediaType().getId();
        long typeIdVideo = videoContentMedia.getContentMediaId().getMediaType().getId();

        List<ContentMedia> resultImage = contentMediaRepository.findByTypeId(typeIdImage);
        List<ContentMedia> resultVideo = contentMediaRepository.findByTypeId(typeIdVideo);

        MediaType mediaTypeImage = contentMedia.getContentMediaId().getMediaType();
        MediaType mediaTypeVideo = videoContentMedia.getContentMediaId().getMediaType();

        ContentMedia contentMediaImage = resultImage.get(0);
        ContentMedia contentMediaVideo = resultVideo.get(0);

        assertThat(resultImage).hasSize(1).isNotNull()
                .allMatch(r -> r.getContentMediaId().getContentId().equals(contentMedia.getContentMediaId().getContentId()))
                .allMatch(r -> r.getContentMediaId().getMediaType().getId().equals(contentMedia.getContentMediaId().getMediaType().getId()));

        assertThat(resultVideo).hasSize(1).isNotNull()
                .allMatch(r -> r.getContentMediaId().getContentId().equals(contentMediaVideo.getContentMediaId().getContentId()))
                .allMatch(r -> r.getContentMediaId().getMediaType().getId().equals(contentMediaVideo.getContentMediaId().getMediaType().getId()));

        assertThat(contentMediaImage.getContentMediaId().getMediaType()).isNotNull()
                .matches(m -> m.getId().equals(mediaTypeImage.getId()))
                .matches(m -> m.getType().equals(mediaTypeImage.getType()));

        assertThat(contentMediaVideo.getContentMediaId().getMediaType()).isNotNull()
                .matches(m -> m.getId().equals(mediaTypeVideo.getId()))
                .matches(m -> m.getType().equals(mediaTypeVideo.getType()));

        assertArrayEquals(contentMediaImage.getMedia(), contentMedia.getMedia());
        assertArrayEquals(contentMediaVideo.getMedia(), videoContentMedia.getMedia());
    }

    @Test
    public void shouldCorrectReturnMediaByContentMediaId() {
        contentMediaRepository.save(contentMedia);
        contentMediaRepository.save(videoContentMedia);

        byte[] media = contentMediaRepository.findMediaByContentMediaId(videoContentMedia.getContentMediaId());

        assertArrayEquals(media, videoContentMedia.getMedia());
    }
}
