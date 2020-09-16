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
    public String addForm(@ModelAttribute Advert advert, Principal principal) {
        String userName = principal.getName();
        User loggedUser = userRepository.findFirstByUsername(userName);
        advert.setUser(loggedUser);
        advertRepository.save(advert);
        return "redirect:/";
    }

    @RequestMapping("/ads-all")
    public String allAds(Model model) {
        List<Advert> all = advertRepository.findAll();
        model.addAttribute("allAds", all);
        return "/ads-all";
    }

    @GetMapping("/advert/delete/{id}")
    public String deleteAd(@PathVariable long id, Model model, Principal principal) {
        Optional<Advert> optionalAdvert = advertRepository.findById(id);

        if (!optionalAdvert.isPresent()) {
            model.addAttribute("errorMessage", "Nie znalazłem podanego ogłoszenia.");
            return "error-app-page";
        }

        String username = principal.getName();
        User currentUser = userRepository.findFirstByUsername(username);
        Long currentUserId = currentUser.getId();

        Advert advert = optionalAdvert.get();
        Long advertUserId = advert.getUser().getId();

        if (currentUserId != advertUserId) {
            model.addAttribute("errorMessage", "Nie możesz usunąć ogłoszenia innego użytkownika.");
            return "error-app-page";
        }

        return "confirm-delete";
    }

    @PostMapping("/advert/delete/{id}")
    // poniższe trzeba też zabezpieczyć przed usuwaniem żądaniem post, gdy użytkownik wyśle np. POSTMANEM
    public String confirmedDelete(@PathVariable Long id, Model model) {

        Optional<Advert> optionalAdvert = advertRepository.findById(id);

        if (!optionalAdvert.isPresent()) {
            model.addAttribute("errorMessage", "Nie ma ogłoszenia o podanym id");
            return "error-app-page";
        }

        Advert advert = optionalAdvert.get();

        advertRepository.delete(advert);
        return "redirect:/user-adverts";
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




}
