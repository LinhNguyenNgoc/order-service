package com.example.order.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private String orderNumber;
    private Double totalPrice;
}