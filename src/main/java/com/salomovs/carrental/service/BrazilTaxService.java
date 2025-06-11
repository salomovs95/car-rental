package com.salomovs.carrental.service;

import org.springframework.stereotype.Component;

import com.salomovs.carrental.model.entity.Rental;
import com.salomovs.carrental.model.enums.RentalConstant;
import com.salomovs.carrental.service.types.TaxService;

@Component
public class BrazilTaxService implements TaxService {
  @Override
  public Double processTaxes(Rental rental) {
    if (rental.calculateInterval() > 12d) return rental.getAmountToPay() * RentalConstant.DAILY_TAX_RATE;
    return rental.getAmountToPay() * RentalConstant.HOUR_TAX_RATE;
  }
}
