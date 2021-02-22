package com.rewardsprogram.model;

import lombok.Data;

import java.util.Date;
@Data
public class Transaction {
    private String transactionId;
    private String customerId;
    private Integer amount;
    private Date transactionDate;

}
