package ru.araok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.araok.domain.ContentRecommended;

public interface ContentRecommendedRepository extends JpaRepository<ContentRecommended, Long> {
}
