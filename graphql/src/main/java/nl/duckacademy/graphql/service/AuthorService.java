package nl.duckacademy.graphql.service;

import nl.duckacademy.graphql.controller.input.AuthorInput;
import nl.duckacademy.graphql.model.Author;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthorService {
    private final List<Author> authorData;

    public AuthorService(List<Author> authorData) {
        this.authorData = authorData;
    }

    public List<Author> getAll() {
        return authorData;
    }

    public Author createAuthor(AuthorInput author, LocalDateTime birthDate) {
        Author authorToCreate = new Author();
        authorToCreate.setId(author.getId());
        authorToCreate.setName(author.getName());
        authorToCreate.setBirthDate(birthDate);
        authorData.add(authorToCreate);
        return authorToCreate;
    }
}
