package com.example.SwiggyService.client;

import com.example.SwiggyService.dto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestaurantServiceClient {
    @Autowired
    private RestTemplate restTemplate;


    public OrderResponseDto fetchOrderStatus(String orderId){
        try {
            return restTemplate.getForObject("http://localhost:8082/restaurant/orders/status/" + orderId, OrderResponseDto.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
