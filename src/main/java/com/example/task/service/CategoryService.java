package com.example.task.service;

import com.example.task.entity.Category;
import com.example.task.payload.ApiResponse;
import com.example.task.repository.CategoryRepository;
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
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse getAllCategory() {
        List<Category> all = categoryRepository.findAll();
        if (all.isEmpty()) {
            return new ApiResponse(false, "category list is empty");
        }
        return new ApiResponse(true, "Category list", all);
    }

    public ApiResponse getCategoryByProductId(Integer product_id) {
        Optional<Category> byProductId = categoryRepository.findByProductId(product_id);
        if (byProductId.isPresent())
            return new ApiResponse(true, "Category details ", byProductId.get());
        return new ApiResponse(false, "Category not found by given ID");
    }
}

