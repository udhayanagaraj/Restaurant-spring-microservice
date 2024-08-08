package com.example.SwiggyService.service;


import com.example.SwiggyService.client.RestaurantServiceClient;
import com.example.SwiggyService.dto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SwiggyService {

    @Autowired
    private RestaurantServiceClient restaurantServiceClient;

    public String greeting(){
        return "Welcome to swiggy App service";
    }

    public OrderResponseDto checkOrderStatus(String orderId){
        return restaurantServiceClient.fetchOrderStatus(orderId);
    }
}


