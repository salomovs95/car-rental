package com.salomovs.carrental.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salomovs.carrental.model.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {}
