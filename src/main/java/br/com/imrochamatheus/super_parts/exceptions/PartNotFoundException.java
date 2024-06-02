package br.com.imrochamatheus.super_parts.exceptions;

public class PartNotFoundException extends RuntimeException{
    public PartNotFoundException (String message) {
        super(message);
    }
}
