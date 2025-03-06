package nl.duckacademy.graphql.controller;

import nl.duckacademy.graphql.model.Category;
import nl.duckacademy.graphql.model.Post;
import nl.duckacademy.graphql.service.PostService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @QueryMapping
    public List<Post> posts() {
        return postService.getAllPosts();
    }

    @QueryMapping
    public List<Post> recentPosts(@Argument Integer count, @Argument Integer offset) {
        return postService.recentPosts(count, offset);
    }

    @QueryMapping
    public Post findPostById(@Argument String id) {
        return postService.findPostById(id);
    }

    @MutationMapping
    public Post createPost(@Argument String id, @Argument String title,
                           @Argument String text, @Argument Category category) {
        return postService.createPost(id, title, text, category);
    }

    @SubscriptionMapping
    public Flux<Post> fetchPosts() {
        return postService.fetchPosts();
    }

}
