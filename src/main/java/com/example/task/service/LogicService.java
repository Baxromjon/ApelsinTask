package com.example.task.service;

import com.example.task.entity.Customer;
import com.example.task.entity.Invoice;
import com.example.task.entity.Order;
import com.example.task.payload.*;
import com.example.task.repository.CustomerRepository;
import com.example.task.repository.DetailRepository;
import com.example.task.repository.InvoiceRepository;
import com.example.task.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Baxromjon
 * 03.02.2022
 **/

@Service
public class LogicService {
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DetailRepository detailRepository;



    public ApiResponse getExpiredInvoices() {
        List<Invoice> invoiceList = invoiceRepository.getExpiredInvoices();
        if (invoiceList.isEmpty())
            return new ApiResponse(false, "Not found expired invoices");
        return new ApiResponse(true, "Expired invoice list.", invoiceList);
    }



    public ApiResponse getWrongDateInvoices() {
        List<String[]> wrongDateInvoices = invoiceRepository.getWrongDateInvoices();
        if (wrongDateInvoices.isEmpty())
            return new ApiResponse(false, "Wrong date invoices not found");
        List<WrongDateInvoicesDto> wrongDateInvoicesDtoList = new ArrayList<>();
        wrongDateInvoices.forEach(strings -> wrongDateInvoicesDtoList.add(new WrongDateInvoicesDto(Integer.parseInt(strings[0]), Timestamp.valueOf(strings[1]).toLocalDateTime(), Integer.parseInt(strings[2]), Timestamp.valueOf(strings[3]).toLocalDateTime())));
        return new ApiResponse(true, "Wrong date invoices.", wrongDateInvoicesDtoList);
    }



    public ApiResponse getOrdersWithoutDetails() {
        LocalDateTime date = LocalDateTime.parse("2016-09-06T00:00:00");
        List<Order> orderList = orderRepository.getOrdersWithoutDetails(date);
        if (orderList.isEmpty())
            return new ApiResponse(false, "Orders not found by given details");
        return new ApiResponse(true, "Orders without details.", orderList);
    }



    public ApiResponse getCustomersWithoutOrders() {
        List<Customer> customerList = customerRepository.getCustomersWithoutOrders();
        if (customerList.isEmpty())
            return new ApiResponse(false, "Customers without orders in 2016year not found");
        return new ApiResponse(true, "Customers without orders in 2016year.", customerList);
    }



    public ApiResponse getCustomersAndCustomersLastOrders() {
        List<String[]> customerList = customerRepository.getCustomersAndCustomersLastOrders();
        if (customerList.isEmpty())
            return new ApiResponse(false, "No customers found with the order");
        List<CustomerLastOrderDto> customers = new ArrayList<>();
        customerList.forEach(strings -> customers.add(new CustomerLastOrderDto(Integer.parseInt(strings[0]), strings[1], Timestamp.valueOf(strings[2]).toLocalDateTime())));
        return new ApiResponse(true, "Customers with order and last order date.", customers);
    }



    public ApiResponse getOverpaidInvoices(){
        List<String[]> overpaidInvoices = invoiceRepository.getOverpaidInvoices();
        if (overpaidInvoices.isEmpty())
            return new ApiResponse(false, "Overpaid invoices not found");
        List<OverpaidInvoiceDto> overpaidInvoiceDtoList = new ArrayList<>();
        overpaidInvoices.forEach(strings -> overpaidInvoiceDtoList.add(new OverpaidInvoiceDto(Integer.parseInt(strings[0]), Double.parseDouble(strings[1]))));
        return new ApiResponse(true, "Overpaid invoices.", overpaidInvoiceDtoList);
    }



    public ApiResponse getHighDemandProducts(){
        List<String[]> highDemandProducts = detailRepository.getHighDemandProducts();
        if (highDemandProducts.isEmpty())
            return new ApiResponse(false, "Products ordered more than 10 times were not found");
        List<HighDemandProductsDto> highDemandProductsDtoList = new ArrayList<>();
        highDemandProducts.forEach(strings -> highDemandProductsDtoList.add(new HighDemandProductsDto(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]))));
        return new ApiResponse(true, "High demand products.", highDemandProductsDtoList);
    }



    public ApiResponse getBulkProducts(){
        List<String[]> bulkProducts = detailRepository.getBulkProducts();
        if (bulkProducts.isEmpty())
            return new ApiResponse(false, "Products normally ordered in bulk have not been found");
        List<BulkProductsDto> bulkProductsDtoList = new ArrayList<>();
        bulkProducts.forEach(strings -> bulkProductsDtoList.add(new BulkProductsDto(Integer.parseInt(strings[0]), Double.parseDouble(strings[1]))));
        return new ApiResponse(true, "Bulk products.", bulkProductsDtoList);
    }



    public ApiResponse getNumberOfProductsInYear(){
        List<String[]> numberOfProductsInYear = orderRepository.getNumberOfProductsInYear();
        if (numberOfProductsInYear.isEmpty())
            return new ApiResponse(false, "In 2016, the product was not sold");
        List<NumberOfProductsInYearDto> numberOfProductsInYearDtoList = new ArrayList<>();
        numberOfProductsInYear.forEach(strings -> numberOfProductsInYearDtoList.add(new NumberOfProductsInYearDto(strings[0], Integer.parseInt(strings[1]))));
        return new ApiResponse(true, "Country and number of products sold in 2016.", numberOfProductsInYearDtoList);
    }



    public ApiResponse getOrdersWithoutInvoices(){
        List<String[]> ordersWithoutInvoices = orderRepository.getOrdersWithoutInvoices();
        if (ordersWithoutInvoices.isEmpty())
            return new ApiResponse(false, "Orders without invoice not found");
        List<OrdersWithoutInvoicesDto> ordersWithoutInvoicesDtoList = new ArrayList<>();
        ordersWithoutInvoices.forEach(strings -> ordersWithoutInvoicesDtoList.add(new OrdersWithoutInvoicesDto(Integer.parseInt(strings[0]), Timestamp.valueOf(strings[1]).toLocalDateTime(), Double.parseDouble(strings[2]))));
        return new ApiResponse(true, "Orders without invoice.", ordersWithoutInvoicesDtoList);

    }
}
