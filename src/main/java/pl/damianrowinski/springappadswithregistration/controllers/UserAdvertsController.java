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

    private String generateAdsListAddModelAttributes(Model model, User user) {
        List<Advert> userAds = advertRepository.findAllByUserOrderByPostedDesc(user);
        model.addAttribute("userAdsList", userAds);
        model.addAttribute("userFullName", user.getFullName());
        return "/user-ads-list";
    }
}
