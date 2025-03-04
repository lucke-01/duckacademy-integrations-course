package duckacademy.nl.graphql.config;

import duckacademy.nl.graphql.model.Author;
import duckacademy.nl.graphql.model.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataBootstrapConfig {
    @Bean
    public List<Author> authorsData() {
        List<Author> authors = new ArrayList<>();
        for (int authorId = 0; authorId < 10; ++authorId) {
            Author author = new Author();
            author.setId(String.valueOf(authorId));
            author.setName("Author " + authorId);
            author.setThumbnail("http://example.com/authors/" + authorId);
            authors.add(author);
        }
        return authors;
    }

    @Bean
    public List<Post> postsData(List<Author> authorsList) {
        List<Post> posts = new ArrayList<>();
        for (int postId = 0; postId < 10; ++postId) {
            for (Author author : authorsList) {
                Post post = new Post();
                post.setId(String.valueOf(postId));
                post.setTitle("Post " + author.getId() + ":" + postId);
                post.setCategory("Post category");
                post.setText("Post " + postId + " + by author " + author.getName());
                post.setAuthor(author);
                posts.add(post);
            }
        }
        return posts;
    }
}