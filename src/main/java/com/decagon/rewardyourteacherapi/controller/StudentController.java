package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.payload.TransferFundsDTO;
import com.decagon.rewardyourteacherapi.payload.UserDTO;
import com.decagon.rewardyourteacherapi.service.TransactionService;
import com.decagon.rewardyourteacherapi.service.UserService;
import com.decagon.rewardyourteacherapi.serviceImpl.PaystackTransactionService;
import com.decagon.rewardyourteacherapi.payload.FundingRequestDTO;
import com.decagon.rewardyourteacherapi.util.Responder;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.decagon.rewardyourteacherapi.service.WalletService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {

  private final UserService userService;

    private final WalletService walletService;
    private  final TransactionService transactionService;

    private final PaystackTransactionService transaction;

    @PostMapping(value = "/update")
    public ResponseEntity<APIResponse> editStudentProfile(@RequestBody UserDTO request) {
        return Responder.okay(userService.updateUserProfile(request));
    }

    @PostMapping("/wallet-fund")
    public ResponseEntity<APIResponse> fundWallet(@RequestParam ("amount")BigDecimal amount) {
        return Responder.okay(walletService.fundStudentWallet(amount));
    }

    @PostMapping("/test")
    public ResponseEntity<?> pay(@RequestParam ("amount") BigDecimal amount) throws Exception {
        return ResponseEntity.ok(transaction.initTransaction(amount));
    }

    @PostMapping("/test/{reference}")
    public ResponseEntity<?> pay(@PathVariable("reference") String request) throws Exception{
        return ResponseEntity.ok(transaction.verifyTransaction(request));
    }

    @PostMapping("/wallet-transfer")
    public ResponseEntity<APIResponse> rewardTeacher(@RequestBody TransferFundsDTO request) {
        return Responder.okay(walletService.transferFunds(request));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<APIResponse> viewStudentByIdAndRole(@PathVariable("id") Long id){
        return Responder.okay((userService.viewStudentProfile(id)));
    }

    @GetMapping("/total-moneysent")
    public ResponseEntity<APIResponse> getTotalMoneySpent(){
        return Responder.okay(transactionService.totalMoneySent());
    }
    
}
