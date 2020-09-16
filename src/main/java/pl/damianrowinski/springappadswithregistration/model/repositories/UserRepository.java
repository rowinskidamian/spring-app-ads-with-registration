package pl.damianrowinski.springappadswithregistration.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.damianrowinski.springappadswithregistration.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByUsername(String userName);


    @Query("SELECT u FROM User u JOIN FETCH u.favouriteAdverts where u.username = ?1")
    User findUserByUsernameWithFavouriteAdverts(String username);

}
