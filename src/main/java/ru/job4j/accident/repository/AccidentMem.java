package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();

    private final Map<Integer, AccidentType> accidentTypes = Map.of(
            1, AccidentType.of(1, "Две машины"),
            2, AccidentType.of(2, "Машина и человек"),
            3, AccidentType.of(3, "Машина и велосипед")
    );

    private final Map<Integer, Rule> rules = Map.of(
            1, Rule.of(1, "Статья 1"),
            2, Rule.of(2, "Статья 2"),
            3, Rule.of(3, "Статья 3")
    );

    private final AtomicInteger counterAccidentId = new AtomicInteger(1);

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public Collection<AccidentType> getAccidentsTypes() {
        return accidentTypes.values();
    }

    public Collection<Rule> getRules() {
        return rules.values();
    }

    public void save(Accident accident) {
        if (accident.getId() != 0) {
            accident.setText(accidents.get(accident.getId()).getText());
            accident.setAddress(accidents.get(accident.getId()).getAddress());
            accident.setType(accidents.get(accident.getId()).getType());
            accidents.put(accident.getId(), accident);
        } else {
            accident.setId(counterAccidentId.intValue());
            accident.setType(accidentTypes.get(accident.getType().getId()));
            accidents.put(accident.getId(), accident);
            counterAccidentId.incrementAndGet();
        }
    }
}
