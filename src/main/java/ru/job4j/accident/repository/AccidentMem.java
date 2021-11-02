package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>(Map.of(
        1, createAccidentInit(
                new Accident(), 1, "accident one", "desc 1", "address 1"
            ),
        2, createAccidentInit(
                new Accident(), 2, "accident two", "desc 2", "address 2"
            )
    ));

    private int counterAccidentId = 3;


    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public Accident createAccidentInit(Accident accident, int id, String name, String text, String address) {
        accident.setId(id);
        accident.setName(name);
        accident.setText(text);
        accident.setAddress(address);
        return accident;
    }

    public void createAccident(Accident accident) {
        accident.setId(counterAccidentId);
        accidents.put(counterAccidentId, accident);
        counterAccidentId++;
    }
}
