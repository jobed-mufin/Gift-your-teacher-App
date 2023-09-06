package com.decagon.rewardyourteacherapi.service;



import com.decagon.rewardyourteacherapi.model.Transaction;
import com.decagon.rewardyourteacherapi.payload.TransferFundsDTO;
import com.decagon.rewardyourteacherapi.payload.UserDTO;

import java.math.BigDecimal;

public interface WalletService {
    UserDTO fundStudentWallet(BigDecimal amount);

    Transaction transferFunds(TransferFundsDTO request);
}
