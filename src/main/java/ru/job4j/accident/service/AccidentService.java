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

     public Set<Rule> fillRulesForAccident(String[] ids, Accident accident) {
        Set<Rule> result;
        if (ids == null) {
            int counter = 0;
            Set<Rule> setRulesBuff = accident.getRules();
            String[] idsIfUpdateAccident = new String[setRulesBuff.size()];
            for (Rule elem : setRulesBuff) {
                idsIfUpdateAccident[counter] = String.valueOf(elem.getId());
                counter++;
            }
            result = findRulesByIds(idsIfUpdateAccident);
        } else {
            result = findRulesByIds(ids);
        }
        return result;
     }

     private Set<Rule> findRulesByIds(String[] ids) {
         Set<Rule> result = new HashSet<>();
         List<Rule> buffList = new ArrayList<>(store.getRules());
         for (String id : ids) {
             for (Rule elem : buffList) {
                 if (elem.getId() == Integer.parseInt(id)) {
                     result.add(elem);
                 }
             }
         }
         return result;
     }
}
