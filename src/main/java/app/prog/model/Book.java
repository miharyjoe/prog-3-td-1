package app.prog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {
    /*
    TODO-4-a: Note that i use serial type for ID in database. What does serial do ?
    Serial permet d'incrémenter automatiquement l'ID
    TODO-4-b: Should I map it with int ? Fix it if there is a problem
    no, It should be an Integer cause int is a primative data type
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String title;
    private String author;
    private Integer pageNumber;

    @CreationTimestamp
    private LocalDate releaseDate;

    public boolean hasAuthor() {
        return author != null;
    }

}
