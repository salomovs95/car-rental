package com.salomovs.carrental.db.entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Rental {
  private final Double HOUR_SECONDS = 3600d;

  private Integer id;
  private LocalDateTime rentAt;
  private LocalDateTime returnAt;
  private Integer amountToPay;

  private Integer vehicleId;
  private Integer customerId;

  public Rental(Integer id,
                LocalDateTime rentAt,
                LocalDateTime returnAt,
                Integer vehicleId,
                Integer customerId) {
    this.id = id;
    this.rentAt = rentAt;
    this.returnAt = returnAt;
    this.vehicleId = vehicleId;
    this.customerId = customerId;
    this.amountToPay = 0;
  }

  public Double calculateInterval() {
    if (returnAt == null) throw new RuntimeException("Can't calculate rent interval if car was not returned.");
    return Duration.between(rentAt, returnAt).getSeconds()/HOUR_SECONDS;
  }

  public Integer getId() { return id; }
  public Integer getVehicleId() { return vehicleId; }
  public LocalDateTime getRentAt() { return rentAt; }
  public LocalDateTime getReturnAt() { return returnAt; }
  public Integer getCustomerId() { return customerId; }
  public Integer getAmountToPay() { return amountToPay; }

  public void setId(Integer id) { this.id = id; }
  public void setVehicleId(Integer vehicleId) { this.vehicleId = vehicleId; }
  public void setRentAt(LocalDateTime rentAt) { this.rentAt = rentAt; }
  public void setReturnAt(LocalDateTime returnAt) { this.returnAt = returnAt; }
  public void setCustomerId(Integer customerId) { this.customerId = customerId; }
  public void setAmountToPay(Integer amountToPay) { this.amountToPay = amountToPay; }

  @Override
  public String toString() {
    String strRental = """
    ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    ::  RENTAL  ::::::::::::::::::::::::::::::::::  ID: %d  ::
    ::  FROM: %s  ::::::  TO: %s  :::::::::::::::  %d days  ::
    ::  Amount to Pay:  ------------------------------  %.2f  ::
    ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    """;
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    return String.format(strRental,
                         id,
                         fmt.format(rentAt),
                         fmt.format(returnAt),
                         Math.round (calculateInterval()),
                         amountToPay/100d);
  }
}
