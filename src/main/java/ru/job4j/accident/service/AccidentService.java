package ru.job4j.accident.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.*;

@Service
public class AccidentService {

    private static final Logger LOG = LogManager.getLogger(AccidentService.class.getName());

    private final AccidentJdbcTemplate store;

    public AccidentService(AccidentJdbcTemplate store) {
        this.store = store;
    }

    public Collection<Accident> getAll() {
        return store.getAll();
    }

    public Collection<AccidentType> getAllAccidentType() {
        return store.getAllAccidentType();
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
        return new HashSet<>(store.findRulesByAccidentId(id));
    }

    private Rule findRuleById(int parseInt) {
        return store.findRuleById(parseInt);
    }

    public Accident findById(int id) {
        return store.findById(id);
    }

    public Collection<Rule> getAllRules() {
        return store.getAllRules();
    }
}
