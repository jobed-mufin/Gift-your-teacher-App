package com.decagon.rewardyourteacherapi.serviceImpl;

import com.decagon.rewardyourteacherapi.exception.UserDoesNotExistException;
import com.decagon.rewardyourteacherapi.exception.UserNotFoundException;
import com.decagon.rewardyourteacherapi.mapper.PayloadToModel;
import com.decagon.rewardyourteacherapi.model.Transaction;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.TransactionDTO;
import com.decagon.rewardyourteacherapi.repository.TransactionRepository;
import com.decagon.rewardyourteacherapi.repository.UserRepository;
import com.decagon.rewardyourteacherapi.service.TransactionService;
import com.decagon.rewardyourteacherapi.util.ContextEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;


    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<TransactionDTO> transactionHistory() {
        User user = userRepository.findByEmail(ContextEmail.getEmail()).orElseThrow(()->
                new UserNotFoundException(String.format("user with email: %s not found", ContextEmail.getEmail())));
        return transactionRepository.findTransactionsBySenderOrRecipient(user, user).stream().map(PayloadToModel::mapTransactToDTO).collect(Collectors.toList());
    }

    @Override
    public BigDecimal totalMoneySent(){
        User user = userRepository.findByEmail(ContextEmail.getEmail()).orElseThrow(()->
                new UserNotFoundException(String.format("user with email: %s not found", ContextEmail.getEmail())));
        BigDecimal total = new BigDecimal(0);
        List<Transaction>  transactions = transactionRepository.findTransactionsBySender(user);
        for(Transaction transaction: transactions){
            if(transaction.getRecipient() != user){
                total = total.add(transaction.getAmount());
            }
        }
        return total;
    }
}
