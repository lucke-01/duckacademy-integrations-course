package duckacademy.nl.graphql.controller;


import duckacademy.nl.graphql.model.Author;
import duckacademy.nl.graphql.model.Post;
import duckacademy.nl.graphql.service.AuthorService;
import duckacademy.nl.graphql.service.PostService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

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

    @QueryMapping
    public Author authorById(@Argument String authorId) {
        return authorService.findById(authorId);
    }

    //this schema mapping is more complex so cannot be implemented by default
    @SchemaMapping(typeName = "Author", field = "posts")
    public List<Post> getAuthorPost(Author author) {
        return postService.getAuthorPosts(author.getId());
    }

}