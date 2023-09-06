package com.decagon.rewardyourteacherapi.payload;

import java.util.Date;

public class MessageDTO {

    private long id;
    private UserDTO sender;
    private UserDTO receiver;
    private String content;
    private Date createdAt;
}
