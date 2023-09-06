package com.decagon.rewardyourteacherapi.util;


import com.decagon.rewardyourteacherapi.payload.APIResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class Responder {

    public static ResponseEntity<APIResponse> okay(Object response){
        return  new  ResponseEntity<>(new APIResponse("Request Successful", true, response), HttpStatus.OK);
    }

    public static ResponseEntity<APIResponse> notFound(String message){
        return  new ResponseEntity<>(new APIResponse(message,true, null), HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<APIResponse> alreadyExists(String message){
        return  new ResponseEntity<>(new APIResponse(message, true, null), HttpStatus.CONFLICT);
    }

    public static ResponseEntity<APIResponse> doesNotExists(String message){
        return  new ResponseEntity<>(new APIResponse(message, true, null), HttpStatus.CONFLICT);
    }

    public static ResponseEntity<APIResponse> unAuthorized(String message){
        return  new ResponseEntity<>(new APIResponse(message, true, null), HttpStatus.UNAUTHORIZED);
    }

}
