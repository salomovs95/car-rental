package com.salomovs.carrental.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(name="tbl_vehicles")
@Getter @Setter @AllArgsConstructor
public class Vehicle {
  @Id @GeneratedValue(strategy=GenerationType.UUID)
  private Integer id;
  private String model;
  private String brand;

  private Integer dailyPrice;
  private Integer hourPrice;

  private Plate plate;

}
