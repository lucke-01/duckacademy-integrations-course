package duckacademy.nl.graphql.controller;


import duckacademy.nl.graphql.controller.input.AuthorInput;
import duckacademy.nl.graphql.model.Author;
import duckacademy.nl.graphql.model.Post;
import duckacademy.nl.graphql.service.AuthorService;
import duckacademy.nl.graphql.service.PostService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class PostController {

    private final PostService postService;
    private final AuthorService authorService;

    public PostController(PostService postService, AuthorService authorService) {
        this.postService = postService;
        this.authorService = authorService;
    }

    @QueryMapping
    public Post postById(@Argument String id) {
        return postService.findById(id);
    }

    @QueryMapping
    public List<Post> recentPosts(@Argument int count, @Argument int offset) {
        return postService.getRecentPosts(count, offset);
    }

    @QueryMapping
    public List<Post> posts() {
        return postService.getPosts();
    }

    @MutationMapping
    public Post createPost(@Argument String title, @Argument String text,
                           @Argument String category) {

        Post post = new Post();
        post.setId(UUID.randomUUID().toString());
        post.setTitle(title);
        post.setText(text);
        post.setCategory(category);

        postService.savePost(post);

        return post;
    }

    @MutationMapping
    public Author createAuthor(@Argument AuthorInput author) {
        return authorService.createAuthor(author);
    }

    //IMPORTANT SCHEMA mapping affect to a property of a Type no to all the type as @query mapping
    //this schema mapping is also implemented by default
    @SchemaMapping(typeName = "Post", field = "author")
    public Author getAuthor(Post post) {
        return post.getAuthor();
    }

    //type mapping?
    /*@SchemaMapping(typeName = "Post", field = "author")
    public Author getAuthor(Post post) {
        return post.getAuthor();
    }

    @SchemaMapping(typeName = "Post", field = "first_author")
    public Author getFirstAuthor(Post post) {
        return post.getFirstAuthor();
    }

    @SchemaMapping(typeName = "Author", field = "posts")
    public List<Post> getPosts(Author author) {
        return author.getPosts();
    }*/
}