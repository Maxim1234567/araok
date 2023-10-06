package ru.araok.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.araok.domain.Language;

import java.util.List;

@RepositoryRestResource(path = "language")
public interface LanguageRepository extends CrudRepository<Language, Long> {
    @Override
    List<Language> findAll();
}
