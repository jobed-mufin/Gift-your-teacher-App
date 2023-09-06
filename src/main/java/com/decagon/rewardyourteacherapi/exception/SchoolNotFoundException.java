package com.decagon.rewardyourteacherapi.exception;

public class SchoolNotFoundException extends RuntimeException{
    public SchoolNotFoundException(String message){
        super(message);
    }
}
