package ru.job4j.accident.control;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.AuthorityRepository;
import ru.job4j.accident.repository.UserRepository;

@Controller
public class RegControl {

    private final PasswordEncoder encoder;

    private final UserRepository users;

    private final AuthorityRepository authorityRepository;

    public RegControl(PasswordEncoder encoder,
                      UserRepository users,
                      AuthorityRepository authorityRepository) {
        this.encoder = encoder;
        this.users = users;
        this.authorityRepository = authorityRepository;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorityRepository.findByAuthority("ROLE_USER"));
        try {
            users.save(user);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            String errorMessage = "Пользователь с таким именем уже существует! Выберите другое имя.";
            model.addAttribute("errorMessage", errorMessage);
            return "reg";
        }
        return "redirect:login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
