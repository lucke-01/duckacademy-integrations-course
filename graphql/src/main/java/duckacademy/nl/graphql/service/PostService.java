package duckacademy.nl.graphql.service;

import duckacademy.nl.graphql.exception.NotFoundException;
import duckacademy.nl.graphql.model.Author;
import duckacademy.nl.graphql.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final List<Post> posts;

    public PostService(List<Post> posts) {
        this.posts = posts;
    }

    public Post findById(String id) {
        return posts.stream()
                .filter(p->p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Not found", "not found"));
    }
    public List<Post> getRecentPosts(int count, int offset) {
        return posts.stream()
                .skip(offset)
                .limit(count)
                .collect(Collectors.toList());
    }
    public List<Post> getPosts() {
        return posts;
    }

    public List<Post> getAuthorPosts(String authorId) {
        return posts.stream()
                .filter(post -> authorId.equals(post.getAuthor().getId()))
                .collect(Collectors.toList());
    }

    public void savePost(Post post) {
        posts.add(post);
    }
}