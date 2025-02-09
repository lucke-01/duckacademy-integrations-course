package duckacademy.nl.graphql.dao;

import duckacademy.nl.graphql.controller.input.AuthorInput;
import duckacademy.nl.graphql.model.Author;

import java.util.List;
import java.util.UUID;

public class AuthorDao {
    private final List<Author> authors;

    public AuthorDao(List<Author> authors) {
        this.authors = authors;
    }

    public Author getAuthor(String id) {
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