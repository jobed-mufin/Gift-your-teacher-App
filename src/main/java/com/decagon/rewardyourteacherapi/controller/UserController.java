package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.mapper.PayloadToModel;
import com.decagon.rewardyourteacherapi.enums.Role;
import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.payload.LoginDTO;
import com.decagon.rewardyourteacherapi.payload.UserDTO;
import com.decagon.rewardyourteacherapi.service.UserService;
import com.decagon.rewardyourteacherapi.util.Responder;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping(path  ="/register/student")
    public ResponseEntity<APIResponse> registerLocal(@RequestBody UserDTO request) {
        return Responder.okay(userService.signUpUser(request,Role.STUDENT));
    }

    @PostMapping(path  ="/register/teacher")
    public ResponseEntity<APIResponse> registerGoogle(@RequestBody UserDTO request) {
        return Responder.okay(userService.signUpUser(request,Role.TEACHER));
    }

    @PostMapping("/register/student/callback")
    public ResponseEntity<APIResponse> authenticateOauth2User(@RequestBody UserDTO request){
        request.setRole(Role.STUDENT);
        request.setPassword("");

        return Responder.okay(userService.authenticateOauth2User(request));
    }



    @PostMapping("/register/teacher/callback")
    public ResponseEntity<APIResponse> authenticateOauth2Teacher(@RequestBody UserDTO request){
        request.setRole(Role.TEACHER);
        request.setPassword("");
        return Responder.okay(userService.authenticateOauth2User(request));
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody LoginDTO loginDto) {
        return Responder.okay(userService.login(loginDto));
    }

}
