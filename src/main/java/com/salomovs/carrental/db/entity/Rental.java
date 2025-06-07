package com.salomovs.carrental.db.entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Rental {
  private final Double HOUR_SECONDS = 3600d;

  private Integer id;
  private LocalDateTime rentAt;
  private LocalDateTime returnAt;

  private Integer vehicleId;
  private Integer customerId;

  private Integer amountToPay;

  public Double calculateInterval() {
    if (returnAt == null) throw new RuntimeException("Can't calculate rent interval if car was not returned.");
    return Duration.between(rentAt, returnAt).getSeconds()/HOUR_SECONDS;
  }

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
