package pl.damianrowinski.springappadswithregistration.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.damianrowinski.springappadswithregistration.domain.Advert;
import pl.damianrowinski.springappadswithregistration.domain.User;
import pl.damianrowinski.springappadswithregistration.model.repositories.AdvertRepository;
import pl.damianrowinski.springappadswithregistration.model.repositories.UserRepository;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AdvertisementController {

    private final AdvertRepository advertRepository;
    private final UserRepository userRepository;

    @GetMapping("/advertisement-form")
    public String generateForm(Model model) {
        Advert advert = new Advert();
        model.addAttribute("advertisement", advert);
        return "/advertisement-form";
    }


    // poniżej jest opcja 'zaciągania aktualnie zalogowanego użytkownika' przez obiekt Principal
    @PostMapping("/advertisement-form")
    public String addForm(@ModelAttribute Advert advert, Principal principal){
        String userName = principal.getName();
        User loggedUser = userRepository.findFirstByUsername(userName);
        advert.setUser(loggedUser);
        advertRepository.save(advert);
        return "redirect:/";
    }
}
