
package com.restaurante.order_service.dto;

import lombok.Data;

@Data
public class OrderDetailResponseDTO {

    private Long dishId;
    private String dishName;
    private Double price;
    private Integer quantity;
}