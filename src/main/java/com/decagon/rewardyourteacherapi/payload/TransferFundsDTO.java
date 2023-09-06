package com.decagon.rewardyourteacherapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferFundsDTO {
    private Long id;
    private BigDecimal amount;
}
