package com.decagon.rewardyourteacherapi.mapper;

import com.decagon.rewardyourteacherapi.model.Notification;
import com.decagon.rewardyourteacherapi.model.TeacherExtraInfo;
import com.decagon.rewardyourteacherapi.model.Transaction;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.NotificationDTO;
import com.decagon.rewardyourteacherapi.payload.TransactionDTO;
import com.decagon.rewardyourteacherapi.payload.UserDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class PayloadToModel {

    public static String pattern ="dd-MM-YY, hh:MM";

    public static User mapRequestToUser(UserDTO request){
       User user  = new User();
       if(request.getName() != null){
           user.setName(request.getName());
       }
       if(request.getEmail() != null){
           user.setEmail(request.getEmail());
       }
       if(request.getPassword() != null){
           user.setPassword(request.getPassword());
       }
       if(request.getRole() != null){
           user.setRole(request.getRole());
       }
       if(request.getImageUrl() != null){
           user.setProfileImage(request.getImageUrl());
       }
//        if(request.getPhoneNumber() != null){
//            user.setPhoneNumber(request.getPhoneNumber());
//        }
       return user;
    }

    public static User mapDTOToUser(UserDTO userDTO){

       return new User( userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());
    }

    public static  UserDTO mapUserToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        if(user.getName() != null){
            userDTO.setName(user.getName());
        }
        if(user.getProfileImage() != null){
            userDTO.setImageUrl(user.getProfileImage());
        }
        if(user.getEmail() != null){
            userDTO.setEmail(user.getEmail());
        }
        if(user.getWallet() != null){
            userDTO.setWallet(user.getWallet());
        }
        if(user.getSchool() != null){
            userDTO.setSchoolName(user.getSchool().getSchoolName());
        }
        if(user.getPhoneNumber() != null){
            userDTO.setPhoneNumber(user.getPhoneNumber());
        }
        return  userDTO;
    }
    public static  UserDTO mapUserToDTO2(User user, String token){
        return  new UserDTO(user.getId(),user.getName(),user.getProfileImage(),user.getWallet(), token, user.getRole());
    }
    public static NotificationDTO mapNotToDTO(Notification notification){
        NotificationDTO  notificationDTO = new NotificationDTO(notification.getId(), notification.getMessage(), notification.getCreatedAt(), null);
        String date;
        if(LocalDate.now().equals( notificationDTO.getCreatedAt().toLocalDate())){
            date = "Today, " + notificationDTO.getCreatedAt().format(DateTimeFormatter.ofPattern(pattern)).split(" ")[1];
        }
        else{
            date = notificationDTO.getCreatedAt().format(DateTimeFormatter.ofPattern(pattern));
        }
        notificationDTO.setDate(date);
        return notificationDTO;
    }

    public static TransactionDTO mapTransactToDTO(Transaction transaction){
        return  new TransactionDTO(mapUserToDTO(transaction.getSender()), mapUserToDTO(transaction.getRecipient()),
                transaction.getAmount(), transaction.getTransactionDate());
    }

    public static Notification  NotificationMapper(Notification notification){
        Notification notification1 = new Notification();
        if (notification != null){
            notification1.setMessage(notification.getMessage());
        }

        return  notification1;
    }
}


