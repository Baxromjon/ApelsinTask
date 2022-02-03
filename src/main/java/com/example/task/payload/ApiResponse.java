package com.example.task.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by Baxromjon
 * 03.02.2022
 **/


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private boolean isSuccess;
    private String message;
    private Object object;

    public ApiResponse(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public ApiResponse(boolean isSuccess, Object object) {
        this.isSuccess = isSuccess;
        this.object = object;
    }

    public ApiResponse(String message, Object object) {
        this.message = message;
        this.object = object;
    }
}
