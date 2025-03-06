package nl.duckacademy.graphql.service;

import nl.duckacademy.graphql.exception.NotFoundException;
import nl.duckacademy.graphql.model.Category;
import nl.duckacademy.graphql.model.Post;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@Service
public class PostService {
    private final List<Post> postsData;

    public PostService(List<Post> postsData) {
        this.postsData = postsData;
    }

    public List<Post> getAllPosts() {
        return postsData;
    }

    public List<Post> recentPosts(@Argument Integer count, @Argument Integer offset) {
        return postsData.stream()
                .skip(offset)
                .limit(count)
                .toList();
    }

    public Post findPostById(String id) {
        return postsData.stream().filter(post -> id.equalsIgnoreCase(post.getId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Not found", "Post was not found"));
    }

    public List<Post> getAuthorPosts(String id) {
        return postsData.stream()
                .filter(post -> post.getAuthor() != null)
                .filter(post -> id.equalsIgnoreCase(post.getAuthor().getId()))
                .toList();
    }

    public Post createPost(String id, String title,
                           String text, Category category) {
        Post postToCreate = new Post();
        postToCreate.setId(id);
        postToCreate.setTitle(title);
        postToCreate.setText(text);
        postToCreate.setCategory(category);
        this.postsData.add(postToCreate);
        return postToCreate;
    }

    public Flux<Post> fetchPosts() {
        return Flux.fromIterable(this.postsData)
                .delayElements(Duration.ofMillis(100));
    }
}
