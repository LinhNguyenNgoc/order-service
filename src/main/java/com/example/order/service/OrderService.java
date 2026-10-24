package com.example.order.service;

import com.example.order.dto.OrderRequest;
import com.example.order.model.Order;
import com.example.order.repository.OrderRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderService(OrderRepository orderRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Order createOrder(OrderRequest request) {
        // 1. Lưu DB với trạng thái PENDING
        Order order = new Order();
        order.setOrderNumber(request.getOrderNumber());
        order.setTotalPrice(request.getTotalPrice());
        order.setStatus("PENDING");
        
        Order savedOrder = orderRepository.save(order);

        // 2. Bắn sự kiện lên Kafka topic "order-events" chuẩn JSON đơn giản
        String eventMessage = String.format("{\"orderId\": %d, \"orderNumber\": \"%s\", \"totalPrice\": %.2f}", 
                savedOrder.getId(), savedOrder.getOrderNumber(), savedOrder.getTotalPrice());
        
        kafkaTemplate.send("order-events", eventMessage);

        return savedOrder;
    }
}