package ru.araok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.araok.domain.MediaType;

import java.util.Optional;

public interface MediaTypeRepository extends JpaRepository<MediaType, Long> {
    Optional<MediaType> findById(Long id);
}
