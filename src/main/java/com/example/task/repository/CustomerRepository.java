package com.example.task.repository;

import com.example.task.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value =
            "select distinct on(c.id) * " +
                    "from customer as c" +
                    "inner join orders as o on c.id=o.cust_id" +
                    "where c.id in(" +
                    "select c.id " +
                    "from customer as c" +
                    "where c.id not in(" +
                    "select o.cust_id " +
                    "from orders as o " +
                    "where o.date between '2016-01-01 00:00:00' and '2016-12-31 23:59:59.999'" +
                    ")" +
                    ")",
            nativeQuery = true)
    List<Customer> getCustomersWithoutOrders();

    @Query(value =
            "select distinct on(res.id) res.id, res.name, res.date " +
                    "from(" +
                    "select c.id, c.name, o.date" +
                    "from customer c" +
                    "inner join orders o" +
                    "on c.id=o.cust_id" +
                    "order by o.date desc" +
                    ") res",
            nativeQuery = true)
    List<String[]> getCustomersAndCustomersLastOrders();
}
