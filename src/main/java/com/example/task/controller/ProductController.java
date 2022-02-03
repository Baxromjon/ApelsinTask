package com.example.task.controller;

import com.example.task.payload.ApiResponse;
import com.example.task.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by Baxromjon
 * 03.02.2022
 **/


@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllProduct() {
        ApiResponse allProduct = productService.getAllProduct();
        return ResponseEntity.status(allProduct.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(allProduct);
    }

    @GetMapping("/details")
    public ResponseEntity<?> getProductById(@RequestParam Integer product_id) {
        ApiResponse productById = productService.getProductById(product_id);
        return ResponseEntity.status(productById.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(productById);
    }
}
