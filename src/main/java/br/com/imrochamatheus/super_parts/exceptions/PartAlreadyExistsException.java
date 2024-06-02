package br.com.imrochamatheus.super_parts.exceptions;

public class PartAlreadyExistsException extends RuntimeException{
    public PartAlreadyExistsException (String message) {
        super(message);
    }
}
