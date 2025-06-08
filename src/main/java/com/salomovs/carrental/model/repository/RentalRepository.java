package com.salomovs.carrental.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salomovs.carrental.model.entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, Integer> {}
