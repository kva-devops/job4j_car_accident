package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.Map;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = Map.of(
        1, createAccident(
                new Accident(), 1, "accident one", "desc 1", "address 1"
            ),
        2, createAccident(
                new Accident(), 2, "accident two", "desc 2", "address 2"
            )
    );

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public Accident createAccident(Accident accident, int id, String name, String text, String address) {
        accident.setId(id);
        accident.setName(name);
        accident.setText(text);
        accident.setAddress(address);
        return accident;
    }
}
