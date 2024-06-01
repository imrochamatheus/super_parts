package br.com.imrochamatheus.super_parts.exceptions;

public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException (String message) {
        super(message);
    }
}
