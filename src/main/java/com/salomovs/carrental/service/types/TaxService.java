package com.salomovs.carrental.service.types;

import com.salomovs.carrental.model.entity.Rental;

public interface TaxService {
  Double processTaxes(Rental rental);
}
