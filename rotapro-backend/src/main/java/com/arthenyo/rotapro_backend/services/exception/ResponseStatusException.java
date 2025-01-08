package com.arthenyo.rotapro_backend.services.exception;

public class ResponseStatusException extends RuntimeException{
    public ResponseStatusException(String msg) {
        super(msg);
    }
}
