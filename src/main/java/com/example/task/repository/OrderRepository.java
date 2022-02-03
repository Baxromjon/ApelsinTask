package com.example.task.repository;

import com.example.task.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value =
            "select *" +
                    "from orders o" +
                    "inner join (" +
                    "select o.id " +
                    "from orders o" +
                    "except" +
                    "select d.ord_id " +
                    "from detail d" +
                    ") res " +
                    "on o.id=res.id" +
                    "and o.date < ?1",
            nativeQuery = true)
    List<Order> getOrdersWithoutDetails(LocalDateTime date);

    @Query(value =
            "select res.country_name, res.total_orders" +
                    "from(" +
                    "select distinct cus.country as country_name, (" +
                    "select sum(" +
                    "(select count(*) " +
                    " from orders as o " +
                    " where o.cust_id=c.id and " +
                    " date between '2016-01-01 00:00:00' and '2016-12-31 23:59:59.999')" +
                    ") as all_orders " +
                    "from customer as c " +
                    "where c.country=cus.country" +
                    ") as total_orders " +
                    "from customer as cus" +
                    ") as res" +
                    "where res.total_orders>0",
            nativeQuery = true)
    List<String[]> getNumberOfProductsInYear();

    @Query(value =
            "select distinct on(o.id) o.id as order_id, o.date as date, (" +
                    "select sum(res.total_price) " +
                    "from(" +
                    "select (p.price * d.quantity) as total_price " +
                    "from product as p" +
                    "inner join detail as d on d.ord_id=o.id" +
                    "where p.id=d.pr_id) as res" +
                    ") as total_price" +
                    "from orders as o" +
                    "inner join detail as d on d.ord_id=o.id " +
                    "where o.id in(" +
                    "select id " +
                    "from orders " +
                    "except " +
                    "select ord_id " +
                    "from invoice" +
                    ")",
            nativeQuery = true)
    List<String[]> getOrdersWithoutInvoices();
}
