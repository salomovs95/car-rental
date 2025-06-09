package  com.salomovs.carrental.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterVehicleDto(
  @NotNull @NotEmpty String model,
  @NotNull @NotEmpty String brand,
  @NotNull @NotEmpty String plate,
  @NotNull @NotEmpty String country,
  @NotNull @Min(1) Integer dailyPrice,
  @NotNull @Min(1) Integer hourPrice
) {}
