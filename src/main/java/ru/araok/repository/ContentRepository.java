package ru.araok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.araok.domain.Content;

import java.util.List;
import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content, Long> {
    @Query("select c from Content c where c.createDate between CURRENT_DATE and (CURRENT_DATE + 7 DAY)")
    List<Content> findByCreateDateLessThanNow();

    List<Content> findByNameContainingIgnoreCase(String name);

    Optional<Content> findById(Long id);
}
