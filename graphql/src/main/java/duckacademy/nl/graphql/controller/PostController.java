package duckacademy.nl.graphql.controller;


import duckacademy.nl.graphql.controller.input.AuthorInput;
import duckacademy.nl.graphql.dao.AuthorDao;
import duckacademy.nl.graphql.dao.PostDao;
import duckacademy.nl.graphql.model.Author;
import duckacademy.nl.graphql.model.Post;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class PostController {

    private final PostDao postDao;
    private final AuthorDao authorDao;

    public PostController(PostDao postDao, AuthorDao authorDao) {
        this.postDao = postDao;
        this.authorDao = authorDao;
    }
    @QueryMapping
    public Post postById(@Argument String id) {
        return postDao.findById(id);
    }
    @QueryMapping
    public List<Post> recentPosts(@Argument int count, @Argument int offset) {
        return postDao.getRecentPosts(count, offset);
    }
    @QueryMapping
    public List<Post> posts() {
        return postDao.getPosts();
    }

    @MutationMapping
    public Post createPost(@Argument String title, @Argument String text,
                           @Argument String category, @Argument String authorId) {

        Post post = new Post();
        post.setId(UUID.randomUUID().toString());
        post.setTitle(title);
        post.setText(text);
        post.setCategory(category);
        post.setAuthorId(authorId);

        postDao.savePost(post);

        return post;
    }
    @MutationMapping
    public Author createAuthor(@Argument AuthorInput author) {
        return authorDao.createAuthor(author);
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