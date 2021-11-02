package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import java.util.*;

@Controller
public class IndexControl {

    private final AccidentService accidentService = new AccidentService();

    @GetMapping("/")
    public String index(Model model) {
        Collection<Accident> buff = accidentService.getAll();
        model.addAttribute("accidentsList", buff);
        return "index";    }
}

