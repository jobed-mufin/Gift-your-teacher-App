package com.decagon.rewardyourteacherapi.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class NotificationDTO {
    private long id;
    private String message;
    private LocalDateTime createdAt;
    private String date;
}
