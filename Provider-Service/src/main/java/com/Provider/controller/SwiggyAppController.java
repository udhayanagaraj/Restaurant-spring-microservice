package com.example.SwiggyService.controller;

import com.example.SwiggyService.dto.OrderResponseDto;
import com.example.SwiggyService.service.SwiggyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swiggy")
public class SwiggyAppController {
    @Autowired
    private SwiggyService service;

    @GetMapping("/home")
    public String greetingMessage(){
        return service.greeting();
    }


    @GetMapping("/{orderId}")
    public OrderResponseDto checkOrderStatus(@PathVariable String orderId){
        return service.checkOrderStatus(orderId);
    }
}
