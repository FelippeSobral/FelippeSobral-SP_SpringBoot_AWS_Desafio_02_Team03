package com.team03.challenge02.exception;

public class EntityIdNotFoundException extends RuntimeException{

    public EntityIdNotFoundException (String error){
        super(error);
    }
}
