package pl.damianrowinski.springappadswithregistration.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.damianrowinski.springappadswithregistration.domain.entities.Advert;
import pl.damianrowinski.springappadswithregistration.domain.entities.User;

import java.util.List;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {

    List<Advert> findAllByUserOrderByPostedDesc(User user);

    List<Advert> findFirst5ByOrderByPostedDesc();

    @Query("SELECT a FROM Advert a ORDER BY a.posted DESC")
    List<Advert> findAllOrderByPostedAscending();


}
