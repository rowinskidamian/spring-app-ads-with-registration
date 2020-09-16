package pl.damianrowinski.springappadswithregistration.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.damianrowinski.springappadswithregistration.model.dtos.UserAddDTO;
import pl.damianrowinski.springappadswithregistration.services.impl.UserRegistrationServiceImpl;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserRegistrationServiceImpl userRegistrationService;

    @GetMapping
    public String getRegistration(Model model) {
        UserAddDTO userAddDTO = new UserAddDTO();
        model.addAttribute("userData", userAddDTO);
        return "/registration-form";
    }

    @PostMapping
    public String processRegistration(@ModelAttribute UserAddDTO userData) {
        userRegistrationService.register(userData);
        return "redirect:/";
    }
}
