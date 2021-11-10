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

    public Collection<Accident> getAll() {
        return store.getAll();
    }

    public Collection<AccidentType> getAllAccidentType() {
        return store.getAllAccidentType();
    }
//
    public void addAccidentToStore(Accident accident, String[] ids) {
        if (ids != null) {
            for (String id : ids) {
                accident.addRule(findRuleById(Integer.parseInt(id)));
            }
        }
        store.save(accident);
    }

    private Rule findRuleById(int parseInt) {
        return store.findRuleById(parseInt);
    }

    public Collection<Rule> getAllRules() {
        return store.getAllRules();
    }
}
