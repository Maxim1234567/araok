package ru.araok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.araok.domain.ContentCounter;

import java.util.List;

public interface ContentCounterRepository extends JpaRepository<ContentCounter, Long> {
    List<ContentCounter> findByCountGreaterThan(Long count);
}
