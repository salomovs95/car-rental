package com.salomovs.carrental.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salomovs.carrental.model.entity.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {}
