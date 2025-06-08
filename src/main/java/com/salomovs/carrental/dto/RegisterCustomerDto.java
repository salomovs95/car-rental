package com.salomovs.carrental.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterCustomerDto(
  @NotNull @NotEmpty String fullName,
  @NotNull @NotEmpty String taxId,
  @NotNull @NotEmpty @Email String email,
  @NotNull @NotEmpty String phone
) {}
