package duckacademy.nl.graphql.dao;

import duckacademy.nl.graphql.exception.NotFoundException;
import duckacademy.nl.graphql.model.Post;

import java.util.List;
import java.util.stream.Collectors;

public class PostDao {

    private final List<Post> posts;

    public PostDao(List<Post> posts) {
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

    public List<Post> getAuthorPosts(String author) {
        return posts.stream()
                .filter(post -> author.equals(post.getAuthorId()))
                .collect(Collectors.toList());
    }

    public void savePost(Post post) {
        posts.add(post);
    }
}