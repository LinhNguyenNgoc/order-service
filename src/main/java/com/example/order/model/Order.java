package com.example.order.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private String orderNumber;
    private Double totalPrice;
    private String status;
	
	// Thêm 2 trường mới để lưu chi tiết sản phẩm
    private String skuCode;
    private Integer quantity;
}