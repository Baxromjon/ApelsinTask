package com.example.task.repository;

import com.example.task.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetailRepository extends JpaRepository<Detail, Integer> {
    List<Detail> findAllByOrd_Id(Integer order_id);

    @Query(value =
            "select res.product_id, res.quantity " +
                    "from(" +
                    "select p.id as product_id, (" +
                    "select sum(quantity) " +
                    "from detail as d " +
                    "where d.pr_id=p.id" +
                    ")as quantity " +
                    "from product as p) as res " +
                    "where res.quantity>10",
            nativeQuery = true)
    List<String[]> getHighDemandProducts();

    @Query(value =
            "select res.product_id, p.price" +
                    "from product as p" +
                    "inner join(" +
                    "select p.id as product_id, (" +
                    "select avg(d.quantity) " +
                    "from detail as d " +
                    "where d.pr_id=p.id" +
                    ") as avarage_quantity " +
                    "from product as p" +
                    ") as res on p.id=res.product_id" +
                    "where res.avarage_quantity>=8",
            nativeQuery = true)
    List<String[]> getBulkProducts();
}
