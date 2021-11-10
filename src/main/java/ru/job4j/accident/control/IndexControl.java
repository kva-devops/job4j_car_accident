package ru.job4j.accident.control;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Controller
public class IndexControl {

    private static final Logger LOG = LogManager.getLogger(IndexControl.class.getName());

    private final AccidentService accidentService;

    public IndexControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidentService.getAll());
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Collection<AccidentType> types = accidentService.getAllAccidentType();
        Collection<Rule> rules = accidentService.getAllRules();
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        return "/accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        accidentService.addAccidentToStore(accident, ids);
        return "redirect:/";
    }
}
