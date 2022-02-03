package com.example.task.service;

import com.example.task.entity.Product;
import com.example.task.payload.ApiResponse;
import com.example.task.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * created by Baxromjon
 * 03.02.2022
 **/


@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ApiResponse getAllProduct() {
        List<Product> productList = productRepository.findAll();
        if (productList.isEmpty())
            return new ApiResponse(false, "Product list is empty");
        return new ApiResponse(true, "Product list", productList);
    }

    public ApiResponse getProductById(Integer product_id) {
        Optional<Product> optionalProduct = productRepository.findById(product_id);
        if (optionalProduct.isPresent())
            return new ApiResponse(true, "Product details", optionalProduct.get());
        return new ApiResponse(false, "Product not found by given Id");
    }
}
