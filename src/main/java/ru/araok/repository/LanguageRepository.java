package ru.araok.repository;

import org.springframework.data.repository.CrudRepository;
import ru.araok.domain.Language;

import java.util.List;

public interface LanguageRepository extends CrudRepository<Language, Long> {
    @Override
    List<Language> findAll();
}
