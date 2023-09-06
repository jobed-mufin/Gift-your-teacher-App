package com.decagon.rewardyourteacherapi.exception;

public class AuthorizationException extends RuntimeException{
    public AuthorizationException(String message){
        super(message);
    }
}
