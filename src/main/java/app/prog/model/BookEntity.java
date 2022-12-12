package app.prog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {
    @Id
    @Column(name ="book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    private String author;
    private Integer pageNumber;
    private LocalDate releaseDate;
    @ManyToOne
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private AuthorEntity author;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name ="book_category_mapping",
            joinColumns = @JoinColumn(name ="book_id"),
            inverseJoinColumns = @JoinColumn(name ="category_id"))
    @JsonIgnore
    private Set<CategoryEntity> category = new HashSet<>();
    public boolean hasAuthor() {
        return author != null;
    }

}
