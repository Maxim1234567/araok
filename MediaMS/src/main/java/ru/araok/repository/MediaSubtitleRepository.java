package ru.araok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.araok.domain.MediaSubtitle;

import java.util.List;

public interface MediaSubtitleRepository extends JpaRepository<MediaSubtitle, Long> {
    @Query("select ms from MediaSubtitle ms where ms.contentId = :contentId")
    List<MediaSubtitle> findByContentId(@Param("contentId") long contentId);

    @Query("select ms from MediaSubtitle ms where ms.language.id = :languageId")
    List<MediaSubtitle> findByLanguageId(@Param("languageId") long languageId);
}
