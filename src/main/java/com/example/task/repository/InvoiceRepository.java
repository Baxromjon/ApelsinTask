package com.example.task.repository;

import com.example.task.entity.Invoice;
import com.example.task.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    @Query(value =
            "select * " +
                    "from invoice as i" +
                    "where i.issued>i.due",
            nativeQuery = true)
    List<Invoice> getExpiredInvoices();

    @Query(value =
            "select i.id as invoice_id, i.issued as invoice_issued, o.id as order_id, o.date as order_date " +
                    "from invoice as i " +
                    "inner join orders as o on o.id=i.ord_id " +
                    "where i.issued < o.date",
            nativeQuery = true)
    List<String[]> getWrongDateInvoices();

    @Query(value =
            "select main_res.invoice_id, main_res.repaid_summa" +
                    "from(" +
                    "select inv.id as \"invoice_id\", (" +
                    "    select sum(res.amount)-(select amount from invoice i where i.id=inv.id)as \"repaid_summa\"" +
                    "    from (select p.amount from payment p where p.inv_id=inv.id)as res" +
                    "    )" +
                    "from invoice inv" +
                    ")as main_res" +
                    "where repaid_summa>0",
            nativeQuery = true)
    List<String[]> getOverpaidInvoices();
}
