package org.homework.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String name) {
        super("Entity not found with name " + name);
    }
}
