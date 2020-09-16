package pl.damianrowinski.springappadswithregistration.services;

import pl.damianrowinski.springappadswithregistration.model.dtos.AdvertAddDTO;

import java.security.Principal;

public interface AdvertService {
    void saveAdByLoggedUser(AdvertAddDTO advertAddDTO, Principal principal);
}
