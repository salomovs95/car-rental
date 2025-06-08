package com.salomovs.carrental.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salomovs.carrental.model.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {}
