package com.decagon.rewardyourteacherapi.exception;



public class UserDoesNotExistException extends RuntimeException  {

    public UserDoesNotExistException(String message){
        super(message);
    }
}
