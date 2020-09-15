package pl.damianrowinski.springappadswithregistration.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.damianrowinski.springappadswithregistration.domain.Advert;
import pl.damianrowinski.springappadswithregistration.domain.User;
import pl.damianrowinski.springappadswithregistration.model.repositories.AdvertRepository;
import pl.damianrowinski.springappadswithregistration.model.repositories.UserRepository;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserAdvertsController {
    private final AdvertRepository advertRepository;
    private final UserRepository userRepository;

    @RequestMapping("/user-adverts")
    public String generateUserAdverts(Model model, Principal principal) {
        String loggedUserName = principal.getName();
        User loggedUser = userRepository.findFirstByUsername(loggedUserName);
        List<Advert> loggedUserAds = advertRepository.findAllByUserOrderByPostedDesc(loggedUser);
        model.addAttribute("userAdsList", loggedUserAds);
        model.addAttribute("loggedUserName", loggedUser.getFullName());
        return "/user-ads-list";
    }
}
