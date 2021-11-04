package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();

    private final Map<Integer, AccidentType> accidentTypes = Map.of(
            1, AccidentType.of(1, "Две машины"),
            2, AccidentType.of(2, "Машина и человек"),
            3, AccidentType.of(3, "Машина и велосипед")
    );

    private final AtomicInteger counterAccidentId = new AtomicInteger(1);

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public Collection<AccidentType> getAccidentsTypes() {
        return accidentTypes.values();
    }

    public void createAccident(Accident accident) {
        accident.setType(AccidentType.of(accident.getType().getId(), accidentTypes.get(accident.getType().getId()).getName()));
        accident.setId(counterAccidentId.intValue());
        accidents.put(counterAccidentId.intValue(), accident);
        counterAccidentId.incrementAndGet();
    }

    public void updateAccident(Accident accident) {
        for (Map.Entry<Integer, Accident> elem : accidents.entrySet()) {
            if (elem.getValue().getId() == accident.getId()) {
                accidents.put(elem.getKey(), accident);
            }
        }
    }
}
