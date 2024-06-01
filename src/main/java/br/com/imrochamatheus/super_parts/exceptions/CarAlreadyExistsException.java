package br.com.imrochamatheus.super_parts.exceptions;

public class CarAlreadyExistsException extends RuntimeException{
    public CarAlreadyExistsException (String message) {
        super(message);
    }
}
