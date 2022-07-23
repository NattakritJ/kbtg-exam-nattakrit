package com.example.d3springboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product")
    private String product;

    @Column(name = "status")
    private String status;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Order(String product, Customer customer) {
        this.product = product;
        this.customer = customer;
    }

}
