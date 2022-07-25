package ru.job4j.accident.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.*;

/**
 * Service class of business logic
 */
@Service
@RequiredArgsConstructor
public class AccidentService {

    /**
     * DAO for accident model
     */
    private final AccidentRepository accidentRepository;

    /**
     * Method for getting all accidents
     * @return List of accidents
     */
    public List<Accident> getAllAccidents() {
        String anchor = UUID.randomUUID().toString();
        List<Accident> res = accidentRepository.findAll();
        if (res != null) {
            return res;
        } else {
            throw new NullPointerException("An internal error has occurred. Please try again later or contact technical support with the 'anchor'. anchor: " + anchor);
        }
    }

    /**
     * Method for getting all types of accident
     * @return List of types of accident
     */
    public List<AccidentType> getAllAccidentTypes() {
        String anchor = UUID.randomUUID().toString();
        Optional<List<AccidentType>> optionalAccidentTypes = Optional.ofNullable(accidentRepository.findAllAccidentTypes());
        if (optionalAccidentTypes.isEmpty()) {
            throw new NullPointerException("An internal error has occurred. Please try again later or contact technical support with the 'anchor'. anchor: " + anchor);
        } else {
            return optionalAccidentTypes.get();
        }
    }

    /**
     * Method for getting all rules
     * @return List of rules
     */
    public List<Rule> getAllRules() {
        String anchor = UUID.randomUUID().toString();
        Optional<List<Rule>> optionalRules = Optional.ofNullable(accidentRepository.findAllRules());
        if (optionalRules.isEmpty()) {
            throw new NullPointerException("An internal error has occurred. Please try again later or contact technical support with the 'anchor'. anchor: " + anchor);
        } else {
            return optionalRules.get();
        }
    }

    /**
     * Method for creating new accident
     * @param accident - Accident object
     * @param ids - Array of articles that were violated
     */
    public void addAccidentToStore(Accident accident, String[] ids) {
        String anchor = UUID.randomUUID().toString();
        if (ids != null) {
            Set<Rule> rules = new HashSet<>();
            for (String id : ids) {
                if (id != null) {
                    rules.add(findRuleById(Integer.parseInt(id)));
                }
            }
            accident.setRules(rules);
            accidentRepository.save(accident);
        } else {
            Optional<Accident> optionalAccident = accidentRepository.findAccidentByIdForUpdate(accident.getId());
            if (optionalAccident.isPresent()) {
                Accident foundAccident = optionalAccident.get();
                foundAccident.setName(accident.getName());
                accidentRepository.save(foundAccident);
            } else {
                throw new IllegalArgumentException("Accident not found. Actual parameters: accident ID - " + accident.getId() + ". Please contact technical support with the 'anchor'. anchor: " + anchor);
            }
        }
    }

    /**
     * Method for finding accident by accident ID
     * @param id - accident ID
     * @return Accident object
     */
    public Accident findById(int id) {
        String anchor = UUID.randomUUID().toString();
        Optional<Accident> accidentOptional = accidentRepository.findById(id);
        if (accidentOptional.isPresent()) {
            return accidentOptional.get();
        } else {
            throw new IllegalArgumentException("Accident not found. Actual parameters: accident ID - " + id + ". Please contact technical support with the 'anchor'. anchor: " + anchor);
        }
    }

    /**
     * Private method for finding rule by rule ID
     * @param parseInt rule ID
     * @return Rule object
     */
    private Rule findRuleById(int parseInt) {
        String anchor = UUID.randomUUID().toString();
        Optional<Rule> ruleOptional = accidentRepository.findRuleById(parseInt);
        if (ruleOptional.isPresent()) {
            return ruleOptional.get();
        } else {
            throw new IllegalArgumentException("Rule not found. Actual parameters: rule ID - " + parseInt + ". Please contact technical support with the 'anchor'. anchor: " + anchor);
        }
    }
}
