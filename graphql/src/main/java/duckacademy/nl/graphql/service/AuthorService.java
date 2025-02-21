package duckacademy.nl.graphql.service;

import duckacademy.nl.graphql.controller.input.AuthorInput;
import duckacademy.nl.graphql.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {
    private final List<Author> authors;

    public AuthorService(List<Author> authors) {
        this.authors = authors;
    }
    public List<Author> getAll() {
        return this.authors;
    }

    public Author findById(String id) {
        return authors.stream()
                .filter(author -> id.equals(author.getId()))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
    public Author createAuthor(AuthorInput authorInput) {
        Author author = new Author();
        author.setId(UUID.randomUUID().toString());
        author.setName(authorInput.getName());
        author.setThumbnail(authorInput.getThumbnail());
        authors.add(author);

        return author;
    }
}