package pl.damianrowinski.springappadswithregistration.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.damianrowinski.springappadswithregistration.domain.Advert;
import pl.damianrowinski.springappadswithregistration.domain.User;
import pl.damianrowinski.springappadswithregistration.model.repositories.AdvertRepository;
import pl.damianrowinski.springappadswithregistration.model.repositories.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserAdvertsController {
    private final AdvertRepository advertRepository;
    private final UserRepository userRepository;

    @RequestMapping("/user-adverts")
    public String generateUserAdverts(Model model, Principal principal) {
        String loggedUserName = principal.getName();
        User user = userRepository.findFirstByUsername(loggedUserName);

        return generateAdsListAddModelAttributes(model, user);
    }

    @RequestMapping("/user-adverts/{id}")
    public String generateUserAdvertsById(Model model, @PathVariable Long id) {
        Optional<User> userById = userRepository.findById(id);
        if (userById.isPresent()) ;
        User user = userById.get();

        return generateAdsListAddModelAttributes(model, user);
    }

    @GetMapping("/advert/edit/{id}")
    public String generateEditPage(@PathVariable Long id, Model model, Principal principal) {
        Optional<Advert> optionalAdvert = advertRepository.findById(id);

        if (!optionalAdvert.isPresent()) {
            model.addAttribute("errorMessage", "Nie ma ogłoszenia o podanym id");
            return "error-app-page";
        }

        String username = principal.getName();
        User loggedUser = userRepository.findFirstByUsername(username);
        Long idOfUserFromCurrentAdvert = optionalAdvert.get().getUser().getId();
        if (loggedUser.getId() != idOfUserFromCurrentAdvert) {
            model.addAttribute("errorMessage", "Nie możesz edytować ogłoszenia innego użytkownika.");
            return "error-app-page";
        }

        Advert advertToEdit = optionalAdvert.get();
        model.addAttribute("advertisement", advertToEdit);

        return "advertisement-form-edit";
    }


    @PostMapping("/advert/edit/{id}")
    public String editedAdvert(@ModelAttribute Advert advert) {
        advertRepository.save(advert);
        return "redirect:/user-adverts";
    }


    private String generateAdsListAddModelAttributes(Model model, User user) {
        List<Advert> userAds = advertRepository.findAllByUserOrderByPostedDesc(user);
        model.addAttribute("userAdsList", userAds);
        model.addAttribute("userFullName", user.getFullName());
        return "/user-ads-list";
    }
}
