package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.payload.MailDTO;
import com.decagon.rewardyourteacherapi.service.NotificationService;
import com.decagon.rewardyourteacherapi.util.Responder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
public class  MailController {

    @Autowired
    NotificationService notificationService;

    @PostMapping("/send")    
    ResponseEntity<APIResponse> sendEmail(@RequestParam("email") String email, @RequestParam("message") String message,
                                          @RequestParam("subject") String subject){
        User user = new User(email);
        List<MailDTO> mailList = new ArrayList<>();
        mailList.add(new MailDTO(user, subject, message));
        return Responder.okay(notificationService.SendEmail(mailList));
    }
}
