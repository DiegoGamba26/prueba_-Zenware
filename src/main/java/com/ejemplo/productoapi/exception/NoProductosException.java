package com.ejemplo.productoapi.exception;

public class NoProductosException extends RuntimeException {
    public NoProductosException() {
        super("No existen productos registrados.");
    }
}
