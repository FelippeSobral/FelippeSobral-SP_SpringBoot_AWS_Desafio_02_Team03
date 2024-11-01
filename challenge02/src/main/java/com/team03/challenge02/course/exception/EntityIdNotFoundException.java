package com.team03.challenge02.course.exception;

public class EntityIdNotFoundException extends RuntimeException{

    public EntityIdNotFoundException (String error){
        super(error);
    }
}
