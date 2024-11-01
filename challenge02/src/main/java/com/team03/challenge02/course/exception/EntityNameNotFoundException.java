package com.team03.challenge02.course.exception;

public class EntityNameNotFoundException extends RuntimeException{
    public EntityNameNotFoundException(String error){
        super(error);
    }
}
