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
public class WrongDateInvoicesDto {
    private Integer invoiceId;
    private LocalDateTime invoiceIssued;
    private Integer orderId;
    private LocalDateTime orderDate;
}
