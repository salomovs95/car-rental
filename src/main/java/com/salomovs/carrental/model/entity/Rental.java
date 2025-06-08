package com.salomovs.carrental.model.entity;

import java.time.Duration;
import java.time.LocalDateTime;

import com.salomovs.carrental.model.enums.RentalConstant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(name="tbl_rentals")
@Getter @Setter @AllArgsConstructor
public class Rental {
  @Id @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
  private LocalDateTime rentAt;
  private LocalDateTime returnAt;

  private Integer vehicleId;
  private Integer customerId;

  private Integer amountToPay;

  public Double calculateInterval() {
    if (returnAt == null) throw new RuntimeException("Can't calculate rent interval if car was not returned.");
    return Duration.between(rentAt, returnAt).getSeconds()/RentalConstant.HOUR_SECONDS;
  }
}
