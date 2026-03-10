package org.example.product_management.exception;

public class ResourceConflictException extends  RuntimeException{
    public ResourceConflictException (String message) {
        super (message);
    }
}
