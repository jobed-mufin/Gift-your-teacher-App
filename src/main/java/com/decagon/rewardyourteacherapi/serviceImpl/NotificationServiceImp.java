package com.decagon.rewardyourteacherapi.serviceImpl;


import com.decagon.rewardyourteacherapi.enums.Role;
import com.decagon.rewardyourteacherapi.exception.UserNotFoundException;
import com.decagon.rewardyourteacherapi.mapper.PayloadToModel;
import com.decagon.rewardyourteacherapi.model.Message;
import com.decagon.rewardyourteacherapi.model.Notification;
import com.decagon.rewardyourteacherapi.model.Transaction;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.MailDTO;
import com.decagon.rewardyourteacherapi.payload.NotificationDTO;
import com.decagon.rewardyourteacherapi.repository.NotificationRepository;
import com.decagon.rewardyourteacherapi.repository.UserRepository;
import com.decagon.rewardyourteacherapi.service.NotificationService;
import com.decagon.rewardyourteacherapi.util.ContextEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.decagon.rewardyourteacherapi.enums.MessageType.*;


@Service
public class NotificationServiceImp implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Value("${spring.mail.username}")
    private String sender;

    JavaMailSender javaMailSender;

    @Autowired
    public NotificationServiceImp(NotificationRepository notificationRepository, UserRepository userRepository,JavaMailSender javaMailSender) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }
        @Override
    public void  saveTransactionNotification(Transaction transaction){
        List<Notification> notificationList = new ArrayList<>();
        List<MailDTO> mailList = new ArrayList<>();
        String message;
        if(transaction.getSender().getId() == transaction.getRecipient().getId()){
            if(transaction.getSender().getRole().equals(Role.STUDENT)) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                String formatMoney = df.format(transaction.getAmount());
                message = FUNDED_WALLET.getStr() + formatMoney;
                Notification notification = new Notification( transaction.getRecipient(), message);
                notificationList.add(notification);
                mailList.add(new MailDTO(transaction.getRecipient(),  "Funded your wallet" ,message));
            }
            else{
                message = String.format(TEACHER_WITHDRAW.getStr(), transaction.getAmount());
                Notification notification = new Notification( transaction.getRecipient(), message);
                notificationList.add(notification);
                mailList.add(new MailDTO(transaction.getRecipient(), "Withdrawal Successful", message));
            }
        }
        else{
            notificationList.add( new Notification(transaction.getRecipient(), TEACHER_REWARDED.getStr() + transaction.getSender().getName()));
            mailList.add(new MailDTO(transaction.getRecipient(), "You've Been Rewarded", TEACHER_REWARDED.getStr() + transaction.getSender().getName()));
            notificationList.add( new Notification(transaction.getSender(), REWARD_TEACHER.getStr() + transaction.getRecipient().getName()));
            mailList.add(new MailDTO(transaction.getRecipient(), "Your Reward Was Sent", REWARD_TEACHER.getStr() + transaction.getRecipient().getName()));
            }
        notificationRepository.saveAll(notificationList);
        SendEmail(mailList);
    }

    @Override
    public void saveMessageNotification(Message message) {
        Notification notification = new Notification(message.getReceiver(), message.getContent());
        notificationRepository.save(notification);
        List<MailDTO> mailList = new ArrayList<>();
        mailList.add(new MailDTO(message.getReceiver(),
                "You've got mail from"+message.getSender().getName(), message.getContent()));
        SendEmail(mailList);
    }

    public List<SimpleMailMessage> SendEmail(List<MailDTO> mailList) {
        List<SimpleMailMessage> mailMessages = new ArrayList<>();
        for(MailDTO mail : mailList) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(mail.getUser().getEmail());
            mailMessage.setSubject(mail.getSubject());
            mailMessage.setText(mail.getMessage());
            javaMailSender.send(mailMessage);
            mailMessages.add(mailMessage);
        }
        return mailMessages;
    }

    public List<NotificationDTO> retrieveUserNotification(){
        User user = userRepository.findByEmail(ContextEmail.getEmail()).orElseThrow(() -> new UserNotFoundException("user details not fund"));
        List<Notification> notifications = notificationRepository.findNotificationsByUserOrderByCreatedAtDesc(user);
        return  notifications.stream().map(PayloadToModel::mapNotToDTO).collect(Collectors.toList());
    }


}
