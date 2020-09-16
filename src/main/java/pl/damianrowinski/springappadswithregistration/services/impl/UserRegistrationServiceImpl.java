package pl.damianrowinski.springappadswithregistration.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.damianrowinski.springappadswithregistration.domain.entities.User;
import pl.damianrowinski.springappadswithregistration.model.dtos.UserAddDTO;
import pl.damianrowinski.springappadswithregistration.model.repositories.UserRepository;
import pl.damianrowinski.springappadswithregistration.services.UserRegistrationService;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j

public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(UserAddDTO userAddDTO) {

        User user = new User();
        String encodedPassword = passwordEncoder.encode(userAddDTO.getPassword());
        user.setUsername(userAddDTO.getUsername());
        user.setPassword(encodedPassword);
        user.setFirstName(userAddDTO.getFirstName());
        user.setLastName(userAddDTO.getLastName());
        user.setActive(true);

        log.info("Próba zapisu użytkownika do bazy" + user);
        userRepository.save(user);

    }
}
