package com.decagon.rewardyourteacherapi.payload;

import com.decagon.rewardyourteacherapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MailDTO {
    User user;
    String subject ;
    String message;
}
