package com.example.task.repository;

import com.example.task.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "select *" +
            "from category as c" +
            "         inner join product as p on p.category_id = c.id" +
            "where p.id =? 1", nativeQuery = true)
    Optional<Category> findByProductId(Integer product_id);
}
