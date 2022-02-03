package com.example.task.controller;

import com.example.task.payload.ApiResponse;
import com.example.task.payload.OrderDto;
import com.example.task.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * created by Baxromjon
 * 03.02.2022
 **/


@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/details")
    public ResponseEntity<?> getOrderDetailsById(@RequestParam Integer order_id){
        ApiResponse apiResponse = orderService.getOrderDetailsById(order_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> makeOrder(@RequestBody OrderDto orderDto){
        ApiResponse apiResponse = orderService.makeOrder(orderDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
}
