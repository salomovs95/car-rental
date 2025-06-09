package com.salomovs.carrental.dto;

import jakarta.validation.constraints.NotNull;

public record VehicleRentalDto(
  @NotNull  Integer customerId,
  @NotNull Integer vehicleId
) {}
