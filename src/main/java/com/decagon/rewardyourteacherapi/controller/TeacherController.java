package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.payload.UserDTO;
import com.decagon.rewardyourteacherapi.service.UserService;
import com.decagon.rewardyourteacherapi.util.Responder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/update")
    public ResponseEntity<APIResponse> editProfile(@RequestBody UserDTO request) {
        return Responder.okay(userService.updateUserProfile(request));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<APIResponse> viewTeacherByIdAndRole(@PathVariable Long id){
        return Responder.okay((userService.viewTeacherProfile(id)));
    }
    @GetMapping(value = "/search/{name}")
    public ResponseEntity <APIResponse> searchTeacher(@PathVariable(value = "name")String name){
        return Responder.okay(userService.searchTeacher(name));
    }
    @GetMapping("/retrieveTeachers")
    public ResponseEntity<APIResponse> retrieveTeachers(@RequestParam("page") int page, @RequestParam("size") int size){
        return Responder.okay(userService.retrieveTeachers(page, size));
    }

    @PostMapping("/appreciate/{id}")
    public ResponseEntity<APIResponse> appreciateStudent(@PathVariable(name = "id") Long id){
        return Responder.okay(userService.teacherAppreciatesStudent(id));
    }
}
