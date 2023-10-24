package ru.araok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.araok.domain.AgeLimit;

import java.util.List;

public interface AgeLimitRepository extends JpaRepository<AgeLimit, Long> {

}
