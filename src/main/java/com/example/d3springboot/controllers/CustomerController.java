package com.example.d3springboot.controllers;

import com.example.d3springboot.entities.Customer;
import com.example.d3springboot.entities.Order;
import com.example.d3springboot.repositories.CustomerRepository;
import com.example.d3springboot.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;


    @GetMapping("customers")
    public List<Customer> findAll(
    ) {
        return customerRepository.findAll();
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<?> findAllById(
            @PathVariable Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isPresent()){
            return ResponseEntity.ok(customerOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("customer")
    public ResponseEntity<?> createCustomer(
            @RequestBody Customer customer
    ) {
        customerRepository.save(customer);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("customer/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody Customer customer
    ) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (!optionalCustomer.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Customer customer1 = optionalCustomer.get();
        customer1.setName(customer.getName());
        customer1.setAddress(customer.getAddress());
        customerRepository.save(customer1);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("class-room/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id,
            @RequestBody Customer customer
    ) {
        Optional<Customer> optionalClassRoom = customerRepository.findById(id);
        if (!optionalClassRoom.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("customer/{id}/order/add-to-cart")
    public ResponseEntity<?> createOrder(
            @PathVariable Long id,
            @RequestBody Order order
    ) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (!optionalCustomer.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Order new_order = orderRepository.save(order);
        Customer customer = optionalCustomer.get();
        new_order.setCustomer(customer);
        orderRepository.save(new_order);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("customer/{customer_id}/order/{order_id}")
    public ResponseEntity<?> update(
            @PathVariable Long customer_id,
            @RequestParam String status,
            @PathVariable Long order_id) {

        Optional<Customer> optionalCustomer = customerRepository.findById(customer_id);
        Optional<Order> optionalOrder = orderRepository.findById(order_id);
        if (!optionalCustomer.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Customer customer = optionalCustomer.get();
        Order order_detail = optionalOrder.get();
        if (Objects.equals(order_detail.getStatus(), "SEND")) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setTrace("Order is SEND");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Customer order_owner = order_detail.getCustomer();
        if (order_owner != customer) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setTrace("Permission not allow.");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        order_detail.setStatus(status);
        orderRepository.save(order_detail);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("customer/{customer_id}/order/{order_id}")
    public ResponseEntity<?> checkStatus(
            @PathVariable Long order_id) {
        Optional<Order> optionalOrder = orderRepository.findById(order_id);
        if(optionalOrder.isPresent()){
            return ResponseEntity.ok(optionalOrder.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
