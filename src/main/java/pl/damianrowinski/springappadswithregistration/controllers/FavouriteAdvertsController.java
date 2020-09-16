package pl.damianrowinski.springappadswithregistration.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.damianrowinski.springappadswithregistration.domain.Advert;
import pl.damianrowinski.springappadswithregistration.domain.User;
import pl.damianrowinski.springappadswithregistration.model.repositories.AdvertRepository;
import pl.damianrowinski.springappadswithregistration.model.repositories.UserRepository;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/adverts/favourite")
public class FavouriteAdvertsController {

    private final UserRepository userRepository;
    private final AdvertRepository advertRepository;

    @GetMapping("/all")
    private String allFavourites(Principal principal, Model model){
        User userWithAdverts = getLoggedUser(principal);
        Set<Advert> favouriteAdverts = userWithAdverts.getFavouriteAdverts();

        model.addAttribute("favouriteAdsList", favouriteAdverts);
        model.addAttribute("userFullName", userWithAdverts.getFullName());
        return "/ads-favourite-list";
    }

    @RequestMapping("/add/{id}")
    private String addToFavourite(@PathVariable Long id, Principal principal, Model model) {
        User userWithAdverts = getLoggedUser(principal);
        Optional<Advert> optionalAdvert = advertRepository.findById(id);

        if (!optionalAdvert.isPresent()) {
            return returnErrorPage(model, "Nie znalazłem ogłoszenia o id: " + id);
        }
        Set<Advert> favouriteAdverts = userWithAdverts.getFavouriteAdverts();
        Advert advert = optionalAdvert.get();

        favouriteAdverts.add(advert);

        userWithAdverts.setFavouriteAdverts(favouriteAdverts);
        userRepository.save(userWithAdverts);

        model.addAttribute("favouriteAdsList", favouriteAdverts);
        model.addAttribute("userFullName", userWithAdverts.getFullName());
        return "ads-favourite-list";
    }

    @RequestMapping("/delete/{id}")
    private String deleteFromFavourite(@PathVariable Long id, Principal principal, Model model) {
        User userWithAdverts = getLoggedUser(principal);
        Set<Advert> favouriteAdverts = userWithAdverts.getFavouriteAdverts();
        boolean isAdToDeleteInFavourites = favouriteAdverts.stream()
                .anyMatch(advert -> advert.getId() == id);

        if (!isAdToDeleteInFavourites) {
            returnErrorPage(model, "Ogłoszenie, które chcesz usunąć z listy nie znajduje się " +
                    "wśród ulubionych.");
            return "error-app-page";
        }

        Optional<Advert> optionalAdvert = advertRepository.findById(id);
        if (!optionalAdvert.isPresent()) {
            return returnErrorPage(model, "Nie znalazłem ogłoszenia o id: " + id);
        }
        favouriteAdverts.remove(optionalAdvert.get());

        userWithAdverts.setFavouriteAdverts(favouriteAdverts);
        userRepository.save(userWithAdverts);

        model.addAttribute("favouriteAdsList", favouriteAdverts);
        model.addAttribute("userFullName", userWithAdverts.getFullName());
        return "ads-favourite-list";
    }

    private String returnErrorPage(Model model, String errorMessage) {
        model.addAttribute("errorMessage", errorMessage);
        return "error-app-page";
    }

    private User getLoggedUser(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsernameWithFavouriteAdverts(username);
    }
}
