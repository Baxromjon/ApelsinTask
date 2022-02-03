package com.example.task.controller;

import com.example.task.payload.ApiResponse;
import com.example.task.service.CategoryService;
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
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllCategory(){
        ApiResponse apiResponse=categoryService.getAllCategory();
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("getByProduct")
    public ResponseEntity<?> getCategory(@RequestParam Integer product_id){
        ApiResponse categoryByProductId = categoryService.getCategoryByProductId(product_id);
        return ResponseEntity.status(categoryByProductId.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(categoryByProductId);


    }

}
