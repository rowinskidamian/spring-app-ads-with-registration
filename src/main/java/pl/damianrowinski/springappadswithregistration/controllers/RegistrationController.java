package pl.damianrowinski.springappadswithregistration.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.damianrowinski.springappadswithregistration.domain.User;
import pl.damianrowinski.springappadswithregistration.model.repositories.UserRepository;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {

    private final UserRepository userRepository;

    @GetMapping
    public String getRegistration() {
        return "/registration-form";
    }

    @PostMapping
    public String processRegistration(String username, String password, String firstName, String lastName) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setActive(true);
        userRepository.save(user);
        log.info("Zapisany użytkownik " + user);
        return "redirect:/";
    }
}
