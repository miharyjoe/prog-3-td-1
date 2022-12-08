package app.prog.controller.mapper;

import app.prog.controller.request.BookRequest;
import app.prog.controller.request.BookUpdate;
import app.prog.controller.response.BookResponse;
import app.prog.model.Book;
import app.prog.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@AllArgsConstructor
public class BookRestMapper {

    private final BookRepository repository;

    public BookResponse toRest(Book domain) {
        return BookResponse.builder()
                .id(domain.getId())
                .title(domain.getTitle())
                .author(domain.getAuthor())
                .page(domain.getPageNumber())
                .releaseDate(domain.getReleaseDate())
                .hasAuthor(domain.hasAuthor())
                .build();
    }

    public Book toCreate(BookRequest domain) {
        return Book.builder()
                .title(domain.getTitle())
                .author(domain.getAuthor())
                .pageNumber(10)
                .build();
    }

    public Book toUpdate(BookUpdate data) {
        Book toUpdate =  repository.getById(String.valueOf(data.getId()));
        toUpdate.setTitle(data.getTitle());
        toUpdate.setAuthor(data.getAuthor());
        return toUpdate;
    }
}
