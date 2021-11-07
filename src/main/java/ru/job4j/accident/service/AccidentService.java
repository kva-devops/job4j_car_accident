package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.*;

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

    public Collection<Rule> getAllRules() {
        return store.getRules();
    }

    public void addAccidentToStore(Accident accident, String[] ids) {
        if (ids != null) {
            Set<Rule> rules = new HashSet<>();
            for (String id : ids) {
                rules.add(findRuleById(Integer.parseInt(id)));
            }
            accident.setRules(rules);
        } else {
            accident.setRules(findRulesByAccidentId(accident.getId()));
        }
        store.save(accident);
    }

    private Set<Rule> findRulesByAccidentId(int id) {
        for (Accident elem : store.getAccidents()) {
            if (elem.getId() == id) {
                return elem.getRules();
            }
        }
        return null;
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

     public Rule findRuleById(int id) {
        List<Rule> buff = new ArrayList<>(store.getRules());
        return buff.get(id - 1);
     }
}
