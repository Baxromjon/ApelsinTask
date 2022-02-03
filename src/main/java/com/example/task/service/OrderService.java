package com.example.task.service;

import com.example.task.entity.*;
import com.example.task.payload.*;
import com.example.task.repository.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * created by Baxromjon
 * 03.02.2022
 **/


@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DetailRepository detailRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InvoiceRepository invoiceRepository;

    public ApiResponse getOrderDetailsById(Integer order_id) {
        Optional<Order> optionalOrder = orderRepository.findById(order_id);
        if (!optionalOrder.isPresent())
            return new ApiResponse(false, "Order not found by given Id");
        Order order = optionalOrder.get();
        List<Detail> detailList = detailRepository.findAllByOrd_Id(order_id);
        OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
        orderDetailsDto.setOrderId(order.getId());
        orderDetailsDto.setCustomerId(order.getCustomer().getId());
        orderDetailsDto.setDateTime(order.getDate());
        orderDetailsDto.setDetails(detailList);

        return new ApiResponse(true, "Order details.", orderDetailsDto);
    }

    public ApiResponse makeOrder(OrderDto orderDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(orderDto.getCustomerId());
        if (!optionalCustomer.isPresent()) {
            return new ApiResponse(false, "Customer not found by given Id");
        }
        Optional<Product> optionalProduct = productRepository.findById(orderDto.getProductId());
        if (!optionalProduct.isPresent()) {
            return new ApiResponse(false, "Product not found by given Id");
        }
        Product product = optionalProduct.get();

        Order order = new Order();
        order.setDate(LocalDateTime.now());
        order.setCustomer(optionalCustomer.get());
        order = orderRepository.save(order);

        Detail detail = new Detail();
        detail.setOrder(order);
        detail.setProduct(product);
        detail.setQuantity(orderDto.getQuantity());
        detailRepository.save(detail);

        Invoice invoice = new Invoice();
        invoice.setOrder(order);
        invoice.setAmount(product.getPrice().multiply(BigDecimal.valueOf(detail.getQuantity())));
        invoice.setIssued(LocalDateTime.now());
        invoice.setDue(LocalDateTime.now().plusHours(ProjectProperties.TIME_OF_INVOICE_IN_HOURS));
        invoice = invoiceRepository.save(invoice);
        InvoiceDto invoiceDto = new InvoiceDto(invoice.getId());
        return new ApiResponse(true, "Order, Details and Invoice created.", invoiceDto);
    }
}
