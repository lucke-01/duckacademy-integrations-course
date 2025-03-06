package nl.duckacademy.graphql.controller;

import nl.duckacademy.graphql.controller.input.AuthorInput;
import nl.duckacademy.graphql.model.Author;
import nl.duckacademy.graphql.model.Post;
import nl.duckacademy.graphql.service.AuthorService;
import nl.duckacademy.graphql.service.PostService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class AuthorController {
    private final PostService postService;
    private final AuthorService authorService;

    public AuthorController(PostService postService, AuthorService authorService) {
        this.postService = postService;
        this.authorService = authorService;
    }

    @QueryMapping
    public List<Author> authors() {
        return authorService.getAll();
    }

    @SchemaMapping(typeName = "Author", field = "posts")
    public List<Post> getAuthorPost(Author author) {
        return postService.getAuthorPosts(author.getId());
    }

    @MutationMapping
    public Author createAuthor(@Argument AuthorInput author, @Argument LocalDateTime birthDate) {
        return authorService.createAuthor(author, birthDate);
    }
}
