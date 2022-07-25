package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.Optional;

/**
 * DAO interface for Accident model
 */

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Query("SELECT DISTINCT a FROM Accident a "
            + "JOIN FETCH a.type "
            + "JOIN FETCH a.rules")
    List<Accident> findAll();

    @Query("FROM AccidentType")
    List<AccidentType> findAllAccidentTypes();

    @Query("FROM Rule")
    List<Rule> findAllRules();

    @Query("FROM Rule WHERE id = :id")
    Optional<Rule> findRuleById(@Param("id") int id);

    @Query("SELECT DISTINCT a FROM Accident a "
            + "JOIN FETCH a.type "
            + "JOIN FETCH a.rules "
            + "WHERE a.id = :id")
    Optional<Accident> findAccidentByIdForUpdate(@Param("id") int id);
}
