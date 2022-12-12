package app.prog.controller;

import app.prog.controller.mapper.AuthorRestMapper;
import app.prog.controller.mapper.BookRestMapper;
import app.prog.controller.response.AuthorResponse;
import app.prog.controller.response.BookResponse;
import app.prog.controller.response.CreateAuthorResponse;
import app.prog.controller.response.CreateBookResponse;
import app.prog.controller.response.UpdateAuthorResponse;
import app.prog.controller.response.UpdateBookResponse;
import app.prog.model.AuthorEntity;
import app.prog.model.BookEntity;
import app.prog.service.AuthorService;
import app.prog.service.BookService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@AllArgsConstructor
public class AuthorController {
    private final AuthorService service;
    private final AuthorRestMapper mapper;

    @GetMapping("/authors")
    public List<AuthorResponse> getAuthors() {
        return service.getAuthors().stream()
                .map(mapper::toRest)
                .toList();
    }

    @PostMapping("/authors")
    public List<AuthorResponse> createAuthors(@RequestBody List<CreateAuthorResponse> toCreate) {
        List<AuthorEntity> domain = toCreate.stream()
                .map(mapper::toDomain)
                .toList();
        return service.createAuthors(domain).stream()
                .map(mapper::toRest)
                .toList();
    }

    @PutMapping("/authors")
    public List<AuthorResponse> updateAuthors(@RequestBody List<UpdateAuthorResponse> toUpdate) {
        List<AuthorEntity> domain = toUpdate.stream()
                .map(mapper::toDomain)
                .toList();
        return service.updateAuthors(domain).stream()
                .map(mapper::toRest)
                .toList();
    }

    @DeleteMapping("/authors/{authorId}")
    public AuthorResponse deleteAuthor(@PathVariable Integer authorId) {
        try {
            return mapper.toRest(service.deleteAuthor(authorId));
        } catch (RuntimeException exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Author Not Found", exc);
        }
    }
}
