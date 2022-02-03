package com.example.task.controller;

import com.example.task.payload.ApiResponse;
import com.example.task.payload.PaymentDto;
import com.example.task.service.PaymentService;
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
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @GetMapping("/details")
    public ResponseEntity<?> getPaymentDetailsById(@RequestParam Integer id){
        ApiResponse paymentDetailsById = paymentService.getPaymentDetailsById(id);
        return ResponseEntity.status(paymentDetailsById.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(paymentDetailsById);
    }

    @PostMapping
    public ResponseEntity<?> makePayment(@RequestBody PaymentDto paymentDto){
        ApiResponse apiResponse = paymentService.makePayment(paymentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);

    }
}
