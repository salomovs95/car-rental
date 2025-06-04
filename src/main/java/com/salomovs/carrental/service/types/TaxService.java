package com.salomovs.carrental.service.types;

import com.salomovs.carrental.db.entity.Rental;

public interface TaxService {
  Double processTaxes(Rental rental);
}
