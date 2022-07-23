package com.example.d3springboot.repositories;

import com.example.d3springboot.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAll();
    Optional<Customer> findAllById(Long id);
}
