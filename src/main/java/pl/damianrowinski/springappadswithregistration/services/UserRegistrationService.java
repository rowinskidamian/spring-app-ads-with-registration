package pl.damianrowinski.springappadswithregistration.services;

import pl.damianrowinski.springappadswithregistration.model.dtos.UserAddDTO;

public interface UserRegistrationService {

    void register(UserAddDTO userAddDTO);
}
