package ru.araok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.araok.domain.Content;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
