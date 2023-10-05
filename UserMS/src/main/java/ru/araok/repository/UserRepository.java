package ru.araok.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.araok.domain.User;

import java.util.List;

@RepositoryRestResource(path = "user")
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

    @RestResource(rel = "find-by-name")
    List<User> findByName(String name);
}
