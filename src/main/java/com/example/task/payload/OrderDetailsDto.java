package com.example.task.payload;

import com.example.task.entity.Detail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * created by Baxromjon
 * 03.02.2022
 **/


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDto {
    private Integer orderId;
    private Integer customerId;
    private LocalDateTime dateTime;
    private List<Detail> details;
}
