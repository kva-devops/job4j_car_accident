package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.service.AccidentService;

import java.util.*;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        AccidentMem accidentsList = new AccidentMem();
        Map<Integer, Accident> someMap = new HashMap<>();
        Accident accident1 = new Accident();
        accident1.setId(1);
        accident1.setName("Accident One");
        accident1.setText("Desc accident 1");
        accident1.setAddress("Address 1");
        Accident accident2 = new Accident();
        accident2.setId(2);
        accident2.setName("Accident Two");
        accident2.setText("Desc accident 2");
        accident2.setAddress("Address 2");
        someMap.put(1, accident1);
        someMap.put(2, accident2);
        accidentsList.setAccidents(someMap);
        AccidentService accidentService = new AccidentService(accidentsList);
        Collection<Accident> buff = accidentService.getAll();
        model.addAttribute("accidentsList", buff);
        return "index";    }
}

