package app.prog.service;

import app.prog.model.AuthorEntity;
import app.prog.model.BookEntity;
import app.prog.repository.AuthorRepository;
import app.prog.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository repository;

    public List<AuthorEntity> getAuthors() {
        return repository.findAll();
    }

    public List<AuthorEntity> createAuthors(List<AuthorEntity> toCreate) {
        return repository.saveAll(toCreate);
    }

    public List<AuthorEntity> updateAuthors(List<AuthorEntity> toUpdate) {
        return repository.saveAll(toUpdate);
    }

    public AuthorEntity deleteAuthor(int authorEntityid) {
        Optional<AuthorEntity> optional = repository.findById(String.valueOf(authorEntityid));
        if (optional.isPresent()) {
            repository.delete(optional.get());
            return optional.get();
        } else {
            throw new NoSuchElementException("BookEntity." + authorEntityid + " not found");
        }
    }
}
