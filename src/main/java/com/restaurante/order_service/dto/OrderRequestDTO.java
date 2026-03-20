package com.restaurante.order_service.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequestDTO {

    private String name;
    private List<OrderDetailRequestDTO> details;
}