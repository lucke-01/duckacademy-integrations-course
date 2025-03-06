package nl.duckacademy.graphql.controller;

import nl.duckacademy.graphql.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.graphql.test.tester.WebSocketGraphQlTester;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.net.URI;
import java.util.Objects;


@AutoConfigureHttpGraphQlTester
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostControllerTest {
    @LocalServerPort
    int port;
    @Autowired
    private GraphQlTester graphQlTester;

    //Testing Query
    @Test
    void should_get_recentPosts() {
        this.graphQlTester
                .document("""
                        query {
                          recentPosts(count:2, offset:9) {
                            id
                            title
                          }
                        }
                        """)
                //.variable("id", "book-1")
                .execute()
                .path("recentPosts")
                .matchesJson("""
                                   [
                                      {
                                        "id": "9_0",
                                        "title": "title0"
                                      },
                                      {
                                        "id": "0_1",
                                        "title": "title1"
                                      }
                                    ]
                        """);
    }

    //TESTING MUTATION
    @Test
    void should_createPost() {
        this.graphQlTester
                .document("""
                        mutation {
                          createPost(id: "new", title:"newPost", text:"todo", category:PUBLIC) {
                            id
                            title
                            text
                            category
                          }
                        }
                        """)
                .execute()
                .path("createPost")
                .matchesJson("""
                                  {
                                       "id": "new",
                                       "title": "newPost",
                                       "text": "todo",
                                       "category": "PUBLIC"
                                    }
                        """);
    }

    //TESTING SUBSCRIPTION
    @Test
    void should_fetchPosts() {
        WebSocketClient client = new ReactorNettyWebSocketClient();
        URI url = URI.create("ws://localhost:" + port + "/graphql");
        WebSocketGraphQlTester tester = WebSocketGraphQlTester.builder(url, client).build();
        Flux<Post> postFlux = tester
                .document("""
                        subscription {
                          fetchPosts {
                            id
                            title
                          }
                        }
                        """)
                .executeSubscription()
                .toFlux("fetchPosts", Post.class);

        StepVerifier.create(postFlux)
                .expectNextMatches(post -> post.getId().equals("0_0"))
                .expectNextMatches(post -> post.getId().equals("1_0"))
                .expectNextMatches(post -> post.getId().equals("2_0"))
                .thenConsumeWhile(Objects::nonNull)
        ;
    }

}
