package nl.duckacademy.graphql.config;

import nl.duckacademy.graphql.model.Author;
import nl.duckacademy.graphql.model.Category;
import nl.duckacademy.graphql.model.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataBootstrapConfig {
    @Bean
    public List<Post> postData(List<Author> authorData) {
        List<Post> posts = new ArrayList<>();
        for (int index = 0; index < 10; index++) {
            for (Author author : authorData) {
                Post post = new Post();
                post.setId(author.getId() + "_" + index);
                post.setTitle("title" + index);
                post.setText("text" + index);
                post.setCategory(Category.PUBLIC);
                if (index % 2 == 0) {
                    post.setCategory(Category.PRIVATE);
                }
                post.setAuthor(author);
                posts.add(post);
            }
        }
        return posts;
    }

    @Bean
    public List<Author> authorData() {
        List<Author> authors = new ArrayList<>();
        for (int index = 0; index < 10; index++) {
            Author author = new Author();
            author.setId(String.valueOf(index));
            author.setName("name" + index);
            author.setBirthDate(LocalDateTime.now());
            authors.add(author);
        }
        return authors;
    }
}
