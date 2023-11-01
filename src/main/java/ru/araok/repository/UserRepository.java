package ru.araok.repository;

import org.springframework.data.repository.CrudRepository;
import ru.araok.domain.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();
}
