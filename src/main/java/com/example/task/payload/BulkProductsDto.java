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
public class BulkProductsDto {

    private Integer productId;
    private Double price;
}
