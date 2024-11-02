package com.team03.challenge02.exception;

public class EntityNameNotFoundException extends RuntimeException{
    public EntityNameNotFoundException(String error){
        super(error);
    }
}
