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
            Set<Rule> rules = findRulesByIds(ids);
            accident.setRules(rules);
        }
        store.save(accident);
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

     private Set<Rule> findRulesByIds(String[] ids) {
         Set<Rule> result = new HashSet<>();
         for (String id : ids) {
             for (Rule elem : store.getRules()) {
                 if (elem.getId() == Integer.parseInt(id)) {
                     result.add(elem);
                 }
             }
         }
         return result;
     }
}
