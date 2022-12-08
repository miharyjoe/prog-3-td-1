package app.prog.controller;

import app.prog.controller.mapper.BookRestMapper;
import app.prog.controller.request.BookRequest;
import app.prog.controller.request.BookUpdate;
import app.prog.controller.response.BookResponse;
import app.prog.service.BookService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {
    private final BookService service;
    private final BookRestMapper mapper;


    @GetMapping("/books")
    public List<BookResponse> getBooks() {
        return service.getBooks().stream()
                .map(mapper::toRest)
                .toList();
    }

    //TODO: This endpoint does not match with our API. Resolve it in the question-1.
    @PostMapping("/books")
    public List<BookResponse> createBooks(@RequestBody List<BookRequest> toCreate) {
        return service.createBooks(toCreate.stream().map(mapper::toCreate).toList()).stream()
                .map(mapper::toRest)
                .toList();
    }

    //TODO: This endpoint does not match with our API. Resolve it in the question-2-ii.
    @PutMapping("/books")
    public List<BookResponse> updateBooks(@RequestBody List<BookUpdate> toUpdate) {
        return service.updateBooks(toUpdate.stream().map(mapper::toUpdate).toList()).stream()
                .map(mapper::toRest)
                .toList();
    }

    @DeleteMapping("/books/{bookId}")
    public BookResponse deleteBook(@PathVariable Integer bookId, HttpServletResponse response) {
        try {
            return mapper.toRest(service.deleteBook(bookId));
        } catch (RuntimeException exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "book Not Found", exc);
        }
    }
}
