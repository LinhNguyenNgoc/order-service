package com.example.order.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private String orderNumber;
    private Double totalPrice;
	private String skuCode;     // Thêm mã sản phẩm
    private Integer quantity;   // Thêm số lượng sản phẩm
}