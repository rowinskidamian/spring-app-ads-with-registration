package pl.damianrowinski.springappadswithregistration.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.damianrowinski.springappadswithregistration.domain.Advert;

import java.util.List;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {

    @Query("SELECT a FROM Advert a ORDER BY a.posted DESC")
    List<Advert> findAllOrderByPostedAscending();

}
