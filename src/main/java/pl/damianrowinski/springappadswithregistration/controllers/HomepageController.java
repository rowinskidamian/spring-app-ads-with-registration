package pl.damianrowinski.springappadswithregistration.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.damianrowinski.springappadswithregistration.domain.entities.Advert;
import pl.damianrowinski.springappadswithregistration.domain.entities.User;
import pl.damianrowinski.springappadswithregistration.model.repositories.AdvertRepository;
import pl.damianrowinski.springappadswithregistration.model.repositories.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomepageController {
    private final AdvertRepository advertRepository;
    private final UserRepository userRepository;

    @GetMapping
    public String generateHomepage(Model model, Principal principal) {
        List<Advert> adsToShow;

        if (principal!= null) {
            String loggedUserName = principal.getName();
            User loggedUser = userRepository.findFirstByUsername(loggedUserName);
            String loggedUserFirstName = loggedUser.getFirstName();
            model.addAttribute("userFirstName", loggedUserFirstName);

            User userWithAdsList = userRepository.findUserByUsernameWithFavouriteAdverts(loggedUserName);

            Set<Advert> favouriteAdverts = userWithAdsList.getFavouriteAdverts();
            model.addAttribute("favouriteAds", favouriteAdverts);

            adsToShow = advertRepository.findAllOrderByPostedAscending();
        } else {
            adsToShow = advertRepository.findFirst5ByOrderByPostedDesc();
        }

        model.addAttribute("advertisements", adsToShow);

        return "homepage";
    }
}
