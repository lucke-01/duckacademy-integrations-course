# GraphQL Theory

## Basics

Graphql is agnostic about the use of any protocol as HTTP.

But usually it works using HTTP protocol and over the methods GET and POST

### example calling using

``` http request
HTTP POST /graphql
query showing_recent_posts {
    recentPosts(count:10, offset: 0) {
        id
        title
    }
}
```

## Spring boot + GraphQL


## Queries

## Mutations

## Scalars

## Subscriptions



## Error management

Guide: [Error handling guide](https://www.baeldung.com/spring-graphql-error-handling)


