package com.example.RestaurantService.service;

import com.example.RestaurantService.dao.RestaurantOrderDao;
import com.example.RestaurantService.dto.OrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantOrderDao orderDao;


    public String greeting() {
        return "Welcome to swiggy restaurant service";
    }

    public OrderResponseDTO getOrders(String orderId){
        return orderDao.getOrders(orderId);
    }
}
