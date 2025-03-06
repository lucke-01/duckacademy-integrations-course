package nl.duckacademy.graphql.exception;

public class NotFoundException extends RuntimeException {
    private String description;

    public NotFoundException(String message, String description) {
        super(message);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
