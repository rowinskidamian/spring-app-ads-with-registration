package pl.damianrowinski.springappadswithregistration.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Transactional
@Table(name = Advert.TABLE_NAME)
@Getter
@Setter

public class Advert {

    final static String TABLE_NAME = "adverts";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String title;

    @NotNull

    //poniższa adnotacja powoduje, że w bazie kolumna będzie typu TEXT
    @Lob
    private String description;

    private LocalDateTime posted;

    @ManyToOne
    private User user;

    @PrePersist
    private void prePersist() {
        posted = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advert advert = (Advert) o;
        return Objects.equals(id, advert.id) &&
                Objects.equals(title, advert.title) &&
                Objects.equals(description, advert.description) &&
                Objects.equals(posted, advert.posted) &&
                Objects.equals(user, advert.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, posted, user);
    }

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", posted=" + posted +
                ", user=" + user +
                '}';
    }
}
