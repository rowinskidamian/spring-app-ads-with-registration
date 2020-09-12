package pl.damianrowinski.springappadswithregistration.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.damianrowinski.springappadswithregistration.domain.Advert;

import java.util.List;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {

    List<Advert> findAllOrderByPostedDesc();

}
