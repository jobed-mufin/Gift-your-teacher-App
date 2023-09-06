package com.decagon.rewardyourteacherapi.repository;

import com.decagon.rewardyourteacherapi.model.Transaction;
import com.decagon.rewardyourteacherapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTransactionsBySenderOrRecipient(User sender, User recipient);

    List<Transaction> findTransactionsBySender(User user);


}
