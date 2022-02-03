package com.example.task.service;

import com.example.task.entity.Invoice;
import com.example.task.entity.Payment;
import com.example.task.payload.ApiResponse;
import com.example.task.payload.PaymentDto;
import com.example.task.repository.InvoiceRepository;
import com.example.task.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * created by Baxromjon
 * 03.02.2022
 **/


@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    InvoiceRepository invoiceRepository;

    public ApiResponse getPaymentDetailsById(Integer id) {
        Optional<Payment> optional = paymentRepository.findById(id);
        if (optional.isPresent())
            return new ApiResponse(true, "Payment Details:", optional.get());
        return new ApiResponse(false,"Payment not found");
    }

    public ApiResponse makePayment(PaymentDto paymentDto) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(paymentDto.getInvoiceId());
        if (!optionalInvoice.isPresent())
            return new ApiResponse(false, "Invoice not found by given Id.");
        Invoice invoice = optionalInvoice.get();
        Payment payment = new Payment();
        payment.setTime(LocalDateTime.now());
        payment.setAmount(invoice.getAmount());
        payment.setInvoice(invoice);
        payment = paymentRepository.save(payment);
        return new ApiResponse(true, "Payment details.", payment);
    }
}
