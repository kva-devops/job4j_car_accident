package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<String> stringList = new ArrayList<>();
        stringList.add("Vladimir Kutyavin");
        stringList.add("Ivan Ivanov");
        stringList.add("Vasiliy Petrov");
        model.addAttribute("userList", stringList);
        return "index";
    }
}

