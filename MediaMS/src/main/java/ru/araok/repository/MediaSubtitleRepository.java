package ru.araok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.araok.domain.MediaSubtitle;

public interface MediaSubtitleRepository extends JpaRepository<MediaSubtitle, Long> {
    MediaSubtitle findByContentIdAndLanguageCode2(Long contentId, String languageCode2);
}
