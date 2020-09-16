package pl.damianrowinski.springappadswithregistration.model.dtos;

import lombok.Data;

@Data
public class UserAddDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
}
