package com.example.task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * created by Baxromjon
 * 02.02.2022
 **/


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 14)
    private String name;
    @Column(nullable = false, length = 3)
    private String country;
    @Column(columnDefinition = "text")
    private String address;
    @Column(nullable = false, length = 50)
    private String phone;
}
