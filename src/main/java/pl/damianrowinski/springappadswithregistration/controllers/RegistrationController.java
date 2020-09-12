package pl.damianrowinski.springappadswithregistration.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String getRegistration() {
        return "/registration-form";
    }

    @PostMapping
    public String processRegistration(String username, String password, String firstName, String lastName) {
        User user = new User();
        String encodedPassword = passwordEncoder.encode(password);
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setActive(true);
        userRepository.save(user);
        log.info("Zapisany użytkownik " + user);
        return "redirect:/";
    }
}
