package com.restaurante.order_service.services;

import com.restaurante.order_service.dto.*;
import com.restaurante.order_service.entity.Order;
import com.restaurante.order_service.entity.OrderDetail;
import com.restaurante.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }
    public OrderResponseDTO create(OrderRequestDTO request) {

        Order order = new Order();
        order.setName(request.getName());
        order.setDate(LocalDate.now());

        List<OrderDetail> details = new ArrayList<>();

        for (OrderDetailRequestDTO dr : request.getDetails()) {

            OrderDetail detail = new OrderDetail();
            detail.setDishId(dr.getDishId());
            detail.setQuantity(dr.getQuantity());
            detail.setDishName("Plato prueba");
            detail.setPrice(10000.0);
            detail.setOrder(order);

            details.add(detail);
        }

        order.setDetails(details);

        Order saved = repository.save(order);

        return mapToDTO(saved);
    }

    public List<OrderResponseDTO> getAll() {

        List<Order> orders = repository.findAll();
        List<OrderResponseDTO> response = new ArrayList<>();

        for (Order order : orders) {
            response.add(mapToDTO(order));
        }

        return response;
    }

    public OrderResponseDTO getById(Long id) {

        Order order = repository.findById(id).orElse(null);

        if (order == null) {
            return null;
        }

        return mapToDTO(order);
    }

    public OrderResponseDTO update(Long id, OrderRequestDTO request) {

        Order order = repository.findById(id).orElse(null);

        if (order == null) {
            return null;
        }

        order.setName(request.getName());

        List<OrderDetail> details = new ArrayList<>();

        for (OrderDetailRequestDTO dr : request.getDetails()) {

            OrderDetail detail = new OrderDetail();
            detail.setDishId(dr.getDishId());
            detail.setQuantity(dr.getQuantity());
            detail.setDishName("Plato actualizado");
            detail.setPrice(12000.0);
            detail.setOrder(order);

            details.add(detail);
        }

        order.setDetails(details);

        Order updated = repository.save(order);

        return mapToDTO(updated);
    }

    public boolean delete(Long id) {

        Order order = repository.findById(id).orElse(null);

        if (order == null) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }

    private OrderResponseDTO mapToDTO(Order order) {

        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setName(order.getName());
        dto.setDate(order.getDate());

        List<OrderDetailResponseDTO> detailsDTO = new ArrayList<>();

        if (order.getDetails() != null) {
            for (OrderDetail detail : order.getDetails()) {

                OrderDetailResponseDTO d = new OrderDetailResponseDTO();
                d.setDishId(detail.getDishId());
                d.setDishName(detail.getDishName());
                d.setPrice(detail.getPrice());
                d.setQuantity(detail.getQuantity());

                detailsDTO.add(d);
            }
        }

        dto.setDetails(detailsDTO);

        return dto;
    }
}