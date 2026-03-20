package com.restaurante.order_service.dto;

import lombok.Data;

@Data
public class OrderDetailRequestDTO {
    private Long dishId;
    private Integer quantity;
}