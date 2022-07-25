package ru.job4j.accident.control;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Main controller
 */
@RequiredArgsConstructor
@Controller
public class IndexControl {

    /**
     * Main object of business logic
     */
    private final AccidentService accidentService;

    /**
     * GET method for loading index page with list all accidents
     * @param model Model object
     * @return index view
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute(
                "user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute(
                "accidents",
                accidentService.getAllAccidents());
        return "index";
    }

    /**
     * GET method for loading create of accident page
     * @param model Model object
     * @return create view
     */
    @GetMapping("/create")
    public String create(Model model) {
        Collection<AccidentType> types = accidentService.getAllAccidentTypes();
        Collection<Rule> rules = accidentService.getAllRules();
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        return "/accident/create";
    }

    /**
     * POST method for saving new accident
     * @param accident Accident model
     * @param req HttpServletRequest object
     * @return redirect to index page
     */
    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        accidentService.addAccidentToStore(accident, ids);
        return "redirect:/";
    }

    /**
     * GET method for loading update of accident page
     * @param id accident ID
     * @param model Model object
     * @return update view
     */
    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findById(id));
        return "accident/update";
    }
}
