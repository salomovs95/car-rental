package com.salomovs.carrental.model.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(name="tbl_vehicles")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Vehicle {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  private String model;
  private String brand;

  private Integer dailyPrice;
  private Integer hourPrice;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name="value", column=@Column(name="plate_number")),
    @AttributeOverride(name="country", column=@Column(name="plate_country"))
  })
  private Plate plate;

}
