package com.arthenyo.rotapro_backend.services.exception;

public class ObjectNotFound extends RuntimeException{
    public ObjectNotFound(String msg){
        super(msg);
    }
}
