package com.example.websocket.service;

import com.example.websocket.domain.Customer;
import com.example.websocket.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerInfo(String customerName) {
        return customerRepository.getCustomerByName(customerName);
    }
}
