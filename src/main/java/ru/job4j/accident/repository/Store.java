package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;

public interface Store {

    List<Accident> findAllAccidents();

    List<AccidentType> findAllAccidentTypes();

    List<Rule> findAllRules();

    void save(Accident accident);

    void update(Accident accident);

    Accident findById(int id);
}
