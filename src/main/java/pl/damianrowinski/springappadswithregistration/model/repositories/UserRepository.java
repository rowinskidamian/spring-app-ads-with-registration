package pl.damianrowinski.springappadswithregistration.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.damianrowinski.springappadswithregistration.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByUsername(String userName);
}
