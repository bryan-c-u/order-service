package com.restaurante.order_service.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderResponseDTO {

    private Long id;
    private String name;
    private LocalDate date;
    private List<OrderDetailResponseDTO> details;
}