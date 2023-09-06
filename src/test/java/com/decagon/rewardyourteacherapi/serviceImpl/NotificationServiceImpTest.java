package com.decagon.rewardyourteacherapi.serviceImpl;

import com.decagon.rewardyourteacherapi.RewardYourTeacherApiApplication;
import com.decagon.rewardyourteacherapi.enums.MessageType;
import com.decagon.rewardyourteacherapi.enums.Role;
import com.decagon.rewardyourteacherapi.model.Message;
import com.decagon.rewardyourteacherapi.model.Notification;
import com.decagon.rewardyourteacherapi.model.Transaction;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.repository.NotificationRepository;
import com.decagon.rewardyourteacherapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RewardYourTeacherApiApplication.class)

class NotificationServiceImpTest {
    User user1 = new User( 1L,"bukky", "test@gamil.com", "password");
    User user2 = new User( 2L,"george", "test2@gamil.com", "password");
//    String  teacherFunded = "A former student has successfully funded your wallet. Say Hi...";
//    String  fundedTeacher = "You've successfully funded your teacher's wallet with N";
//    String fundedWallet = "You have successfully funded you wallet with N";

    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    UserRepository userRepository;

    @MockBean
    JavaMailSender javaMailSender;
    @Test
    void saveTransactionNotification() throws NullPointerException {
        user1.setRole(Role.STUDENT);
        user2.setRole(Role.TEACHER);
        userRepository.save(user1);
        userRepository.save(user2);
        Transaction transaction1 = new Transaction(1L, user1, user2, new BigDecimal(12000.21));
        Transaction transaction2 = new Transaction(2L, user1, user1, new BigDecimal(5000));
        NotificationServiceImp notService = new NotificationServiceImp(notificationRepository, userRepository,javaMailSender);
        notService.javaMailSender = javaMailSender;
        notService.saveTransactionNotification(transaction1);
        assertNotNull(notificationRepository.findByMessageAndUser(MessageType.TEACHER_REWARDED.getStr(), user2));
        Notification  not = notificationRepository.findByMessageAndUser(MessageType.TEACHER_REWARDED.getStr(), user2).orElse(null);
        assertEquals(MessageType.TEACHER_REWARDED.getStr(), not.getMessage());
        assertNotNull(notificationRepository.findByMessageAndUser(MessageType.REWARD_TEACHER.getStr(), user1).orElse(null));
        Notification not2 = notificationRepository.findByMessageAndUser(MessageType.REWARD_TEACHER.getStr(), user1).orElse(null);
        assertTrue(not2.getMessage().contains(MessageType.REWARD_TEACHER.getStr()));
        notService.saveTransactionNotification(transaction2);
        assertNotNull(notificationRepository.findByMessageAndUser(MessageType.FUNDED_WALLET.getStr() + transaction2.getAmount(), user1));
        not = notificationRepository.findByMessageAndUser(MessageType.FUNDED_WALLET.getStr() + transaction2.getAmount(), user1).orElse(null);
        assertTrue(not.getMessage().contains(MessageType.FUNDED_WALLET.getStr()));
    }

    @Test
    void saveMessageNotification() {
        userRepository.save(user1);
        userRepository.save(user2);
        Message message = new Message(user1, user2, "Hello governor");
        NotificationServiceImp notService = new NotificationServiceImp(notificationRepository, userRepository, javaMailSender);
        notService.javaMailSender= javaMailSender;
        notService.saveMessageNotification(message);
        assertNotNull(notificationRepository.findByMessageAndUser("Hello governor", user2));
    }
}
