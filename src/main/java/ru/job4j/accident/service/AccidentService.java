package ru.job4j.accident.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.*;

@Service
public class AccidentService {

    private static final Logger LOG = LogManager.getLogger(AccidentService.class.getName());

    private final AccidentHibernate store;

    public AccidentService(AccidentHibernate store) {
        this.store = store;
    }

    public List<Accident> getAllAccidents() {
        return store.findAllAccidents();
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
            Accident buff = store.findById(accident.getId());
            accident.setText(buff.getText());
            accident.setAddress(buff.getAddress());
            accident.setType(buff.getType());
            accident.setRules(buff.getRules());
            store.update(accident);
        }
    }

    private Rule findRuleById(int parseInt) {
        return store.findRuleById(parseInt);
    }

    public Accident findById(int id) {
        return store.findById(id);
    }
}
