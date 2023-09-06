package com.decagon.rewardyourteacherapi.serviceImpl;

import com.decagon.rewardyourteacherapi.enums.Role;
import com.decagon.rewardyourteacherapi.exception.*;
import com.decagon.rewardyourteacherapi.mapper.PayloadToModel;
import com.decagon.rewardyourteacherapi.model.Transaction;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.TransferFundsDTO;
import com.decagon.rewardyourteacherapi.payload.UserDTO;
import com.decagon.rewardyourteacherapi.repository.TransactionRepository;
import com.decagon.rewardyourteacherapi.repository.UserRepository;
import com.decagon.rewardyourteacherapi.service.NotificationService;
import com.decagon.rewardyourteacherapi.service.WalletService;
import com.decagon.rewardyourteacherapi.util.ContextEmail;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {
   private final TransactionRepository transactionRepository;
   private final NotificationService notificationService;
   private final UserRepository userRepository;


    @Override
    public UserDTO fundStudentWallet(BigDecimal amount)  {
        User user = userRepository.findByEmail(ContextEmail.getEmail()).orElseThrow(()->
                new UserDoesNotExistException(String.format("User with email, %s not found", ContextEmail.getEmail())));
        BigDecimal balance = user.getWallet();
        balance = balance.add(amount);
        user.setWallet(balance);
        Transaction ts = new Transaction(user, user, amount);
        ts =transactionRepository.save(ts);
        notificationService.saveTransactionNotification(ts);
        return PayloadToModel.mapUserToDTO(userRepository.save(user));
    }

    @Override
    public Transaction transferFunds(TransferFundsDTO request) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails)principal).getUsername();
        //User sender = userRepository.findUserByEmailAndRole(email,Role.STUDENT).orElseThrow(() ->new UserDoesNotExistException(String.format("student with %s not found",email)));
        User sender = userRepository.findByEmail(email).orElseThrow(() -> new UserDoesNotExistException(String.format("Student with %s not found", email)));
        System.out.println(sender.getWallet());
        System.out.println(request.getAmount());
        System.out.println(request.getId());
        if(sender.getWallet().compareTo(request.getAmount()) < 0){
            throw new WalletException("Insufficient funds");
        }
        User receiver = userRepository.findUserByIdAndRole(request.getId(), Role.TEACHER). orElseThrow(()-> new UserDoesNotExistException("user not found"));
        BigDecimal senderBalance = sender.getWallet();
        BigDecimal receiverBalance = receiver.getWallet();
        senderBalance = senderBalance.subtract(request.getAmount());
        receiverBalance = receiverBalance.add(request.getAmount());
        sender.setWallet(senderBalance);
        receiver.setWallet(receiverBalance);
        List<User> users = new ArrayList<>();
        userRepository.saveAll(users);
        Transaction transaction = new Transaction(sender, receiver, request.getAmount());
       transaction= transactionRepository.save(transaction);
        notificationService.saveTransactionNotification(transaction);
        return transaction;

    }
}


