package com.decagon.rewardyourteacherapi.service;


import com.decagon.rewardyourteacherapi.model.Message;
import com.decagon.rewardyourteacherapi.model.Transaction;
import com.decagon.rewardyourteacherapi.payload.MailDTO;
import com.decagon.rewardyourteacherapi.payload.NotificationDTO;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;


public interface NotificationService {
      void  saveTransactionNotification(Transaction transaction);
      void saveMessageNotification(Message message);

      List<SimpleMailMessage> SendEmail(List<MailDTO> mailList);

      List<NotificationDTO> retrieveUserNotification();
}
