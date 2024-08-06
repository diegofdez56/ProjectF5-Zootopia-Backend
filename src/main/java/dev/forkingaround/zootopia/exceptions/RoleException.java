package dev.forkingaround.zootopia.exceptions;

public class RoleException extends RuntimeException {
    
    public RoleException(String message) {
        super(message);
    }

    public RoleException(String message, Throwable cause) {
        super(message, cause);
    }

}
