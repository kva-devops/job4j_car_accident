package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import java.util.Collection;

@Controller
public class AccidentControl {

    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/")
    public String index(Model model) {
        Collection<Accident> buff = accidentService.getAll();
        model.addAttribute("accidentsList", buff);
        return "index";
    }

    @GetMapping("/create")
    public String create() {
        return "/accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        accidentService.addAccidentToStore(accident);
        return "redirect:/";
    }
}
