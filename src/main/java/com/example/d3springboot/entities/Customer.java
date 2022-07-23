package com.example.d3springboot.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customer_table")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private Set<Order> orders;

    public Customer(String name) {
        this.name = name;
    }

}
