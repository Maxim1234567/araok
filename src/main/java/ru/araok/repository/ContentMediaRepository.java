package ru.araok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.araok.domain.ContentMedia;
import ru.araok.domain.ContentMediaId;

import java.util.List;

public interface ContentMediaRepository extends JpaRepository<ContentMedia, ContentMedia> {
    ContentMedia findByContentMediaId(ContentMediaId contentMediaId);

    @Query("select cm from ContentMedia cm where cm.contentMediaId.mediaType.id = :typeId")
    List<ContentMedia> findByTypeId(@Param("typeId") Long typeId);
}
