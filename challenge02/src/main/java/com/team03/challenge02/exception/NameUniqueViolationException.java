package com.team03.challenge02.exception;

public class NameUniqueViolationException extends RuntimeException{

    public NameUniqueViolationException(String error){
        super(error);
    }
}
