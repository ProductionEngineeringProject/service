package ro.unibuc.hello.exception;

public class EntityNotFoundException extends RuntimeException {

    private static final String entityNotFoundTemplate = "Entity: %s was not found";

    public EntityNotFoundException(String title) {
        super(String.format(entityNotFoundTemplate, title));
    }
}
