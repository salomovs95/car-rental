package com.salomovs.carrental.dto;

import java.util.Optional;

public record UpdateCustomerDto(
  Optional<String> fullName,
  Optional<String> taxId,
  Optional<String> email,
  Optional<String> phone
) {}
