package com.salomovs.carrental.model.entity;

import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import com.salomovs.carrental.model.enums.RentalConstant;

@Entity @Table(name="tbl_rentals")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Rental {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  
  private LocalDateTime rentAt;
  private LocalDateTime returnAt;

  private Integer amountToPay;

  @ManyToOne @JoinColumn(name="vehicle_id", referencedColumnName="id")
  private Vehicle vehicle;

  @ManyToOne @JoinColumn(name="customer_id", referencedColumnName="id")
  private Customer customer;

  public Double calculateInterval() {
    if (returnAt == null) throw new RuntimeException("Can't calculate rent interval if car was not returned.");
    return Duration.between(rentAt, returnAt).getSeconds()/RentalConstant.HOUR_SECONDS;
  }
}
