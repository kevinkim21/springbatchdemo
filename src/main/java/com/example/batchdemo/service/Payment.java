package com.example.batchdemo.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    private String id;
    private String amount;
    private String currency;
    private String method;
    private String intent;
    private String description;
    private String status;
    private String created;
    private String updated;
    private String customer;
    private String invoiceNumber;
    private String receiptNumber;
    private String receiptUrl;
    private String linkSelf;
    private String linkActions;
}
