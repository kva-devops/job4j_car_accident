package ru.job4j.accident.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.*;

@Service
public class AccidentService {

    private static final Logger LOG = LogManager.getLogger(AccidentService.class.getName());

    private final AccidentRepository store;

    public AccidentService(AccidentRepository store) {
        this.store = store;
    }

    public List<Accident> getAllAccidents() {
        List<Accident> res = new ArrayList<>();
        store.findAll().forEach(res::add);
        return res;
    }

    public List<AccidentType> getAllAccidentTypes() {
        return store.findAllAccidentTypes();
    }

    public List<Rule> getAllRules() {
        return store.findAllRules();
    }

    public void addAccidentToStore(Accident accident, String[] ids) {
        if (ids != null) {
            Set<Rule> rules = new HashSet<>();
            for (String id : ids) {
                rules.add(findRuleById(Integer.parseInt(id)));
            }
            accident.setRules(rules);
            store.save(accident);
        } else {
            Accident buff = store.findAccidentByIdForUpdate(accident.getId());
            buff.setName(accident.getName());
            store.save(buff);
        }
    }

    private Rule findRuleById(int parseInt) {
        return store.findRuleById(parseInt);
    }

    public Accident findById(int id) {
        return store.findById(id).get();
    }
}
