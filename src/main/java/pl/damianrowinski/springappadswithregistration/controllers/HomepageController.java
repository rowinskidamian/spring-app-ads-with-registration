package pl.damianrowinski.springappadswithregistration.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.damianrowinski.springappadswithregistration.domain.Advert;
import pl.damianrowinski.springappadswithregistration.model.repositories.AdvertRepository;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomepageController {
    private final AdvertRepository advertRepository;

    @GetMapping
    public String prepareHomepage(Model model) {
        List<Advert> allOrderByPostedDesc = advertRepository.findAllOrderByPostedDesc();
        model.addAttribute("adverts", allOrderByPostedDesc);
        return "";
    }
}
