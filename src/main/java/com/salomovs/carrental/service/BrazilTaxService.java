package com.salomovs.carrental.service;

import com.salomovs.carrental.db.entity.Rental;
import com.salomovs.carrental.service.types.TaxService;

public class BrazilTaxService implements TaxService {
  private final Double HOUR_TAX_RATE = 0.15;
  private final Double DAILY_TAX_RATE = 0.25;

  @Override
  public Double processTaxes(Rental rental) {
    if (rental.calculateInterval() > 12d) return rental.getAmountToPay() * DAILY_TAX_RATE;
    return rental.getAmountToPay() * HOUR_TAX_RATE;
  }
}
