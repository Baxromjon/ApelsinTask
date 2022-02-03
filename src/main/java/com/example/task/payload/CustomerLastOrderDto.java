package com.example.task.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * created by Baxromjon
 * 03.02.2022
 **/


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerLastOrderDto {

    private Integer customerId;
    private String customerName;
    private LocalDateTime orderTime;
}
