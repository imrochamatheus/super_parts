package br.com.imrochamatheus.super_parts.exceptions;

public class CarHavePartsException extends RuntimeException{
    public CarHavePartsException (String message) {
        super(message);
    }
}