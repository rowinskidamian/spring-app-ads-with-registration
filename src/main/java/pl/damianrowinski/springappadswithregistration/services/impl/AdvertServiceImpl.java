package pl.damianrowinski.springappadswithregistration.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.damianrowinski.springappadswithregistration.domain.entities.Advert;
import pl.damianrowinski.springappadswithregistration.domain.entities.User;
import pl.damianrowinski.springappadswithregistration.model.dtos.AdvertAddDTO;
import pl.damianrowinski.springappadswithregistration.model.repositories.AdvertRepository;
import pl.damianrowinski.springappadswithregistration.model.repositories.UserRepository;
import pl.damianrowinski.springappadswithregistration.services.AdvertService;

import javax.transaction.Transactional;
import java.security.Principal;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdvertServiceImpl implements AdvertService {
    private final UserRepository userRepository;
    private final AdvertRepository advertRepository;

    @Override
    public void saveAdByLoggedUser(AdvertAddDTO advertAddDTO, Principal principal) {
        String userName = principal.getName();
        User loggedUser = userRepository.findFirstByUsername(userName);
        Advert advert = new Advert();
        advert.setTitle(advertAddDTO.getTitle());
        advert.setDescription(advertAddDTO.getDescription());
        advert.setUser(loggedUser);
        log.info("Zapisano nowego użytkownika: " + advert);
        advertRepository.save(advert);
    }
}
