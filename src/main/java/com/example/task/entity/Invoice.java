package com.example.task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * created by Baxromjon
 * 02.02.2022
 **/


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(optional = false)
    private Order order;
    @Column(nullable = false)
    private BigDecimal amount=BigDecimal.valueOf(8,2);
    @Column(nullable = false)
    private LocalDateTime issued;
    @Column(nullable = false)
    private LocalDateTime due;

}
