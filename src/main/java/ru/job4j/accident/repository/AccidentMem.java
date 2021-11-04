package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();

    private final AtomicInteger counterAccidentId = new AtomicInteger(1);

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void createAccident(Accident accident) {
        accident.setId(counterAccidentId.intValue());
        accidents.put(counterAccidentId.intValue(), accident);
        counterAccidentId.incrementAndGet();
    }

    public void updateAccident(Accident accident) {
        for (Map.Entry<Integer, Accident> elem : accidents.entrySet()) {
            if (elem.getValue().getId() == accident.getId()) {
                accident.setText(elem.getValue().getText());
                accident.setAddress(elem.getValue().getAddress());
                accidents.put(elem.getKey(), accident);
            }
        }
    }
}
