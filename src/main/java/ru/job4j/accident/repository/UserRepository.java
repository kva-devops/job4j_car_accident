package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.User;


/**
 * DAO interface for User model
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
