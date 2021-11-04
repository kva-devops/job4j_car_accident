package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

@Service
public class AccidentService {

    private final AccidentMem store;

    public AccidentService() {
        this.store = new AccidentMem();
    }

    public Collection<Accident> getAll() {
        return store.getAccidents();
    }

    public Collection<AccidentType> getAllAccidentType() {
        return store.getAccidentsTypes();
    }

    public void addAccidentToStore(Accident accident) {
        if (store.getAccidents().contains(accident)) {
            store.updateAccident(accident);
        } else {
            store.createAccident(accident);
        }
    }

    public Accident findById(int id) {
        Accident result = null;
        for (Accident elem : store.getAccidents()) {
            if (elem.getId() == id) {
                result = elem;
            }
        }
        return result;
    }
}
