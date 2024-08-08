package com.example.RestaurantService.controller;


import com.example.RestaurantService.dto.OrderResponseDTO;
import com.example.RestaurantService.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public String greetMessage(){
        return  restaurantService.greeting();
    }

    @GetMapping("/orders/status/{orderId}")
    public OrderResponseDTO getOrders(@PathVariable String orderId){
        return restaurantService.getOrders(orderId);
    }
}
