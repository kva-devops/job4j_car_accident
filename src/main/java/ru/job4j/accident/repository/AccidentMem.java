package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {

    private Map<Integer, Accident> accidents = new HashMap<>();

    public Map<Integer, Accident> getAccidents() {
        return accidents;
    }

    public void setAccidents(Map<Integer, Accident> someMap) {
        accidents.putAll(someMap);
    }
}
