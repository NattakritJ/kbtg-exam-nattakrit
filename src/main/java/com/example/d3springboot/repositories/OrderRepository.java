package com.example.d3springboot.repositories;

import com.example.d3springboot.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();
    Optional<Order> findAllById(Long id);
}
