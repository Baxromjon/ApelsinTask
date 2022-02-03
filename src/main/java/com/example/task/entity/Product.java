package com.example.task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * created by Baxromjon
 * 02.02.2022
 **/


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 10)
    private String name;
    @ManyToOne(optional = false)
    private Category category;
    @Column(length = 20)
    private String description;
    @Column(nullable = false)
    private BigDecimal price=BigDecimal.valueOf(6,2);
    @Column(length = 1024)
    private String photo;
}
