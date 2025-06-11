package  com.salomovs.carrental.dto;

import java.util.Optional;

public record UpdateVehicleDto(
  Optional<String> model,
  Optional<String> brand,
  Optional<String> plate,
  Optional<String> country,
  Optional<Integer> dailyPrice,
  Optional<Integer> hourPrice
) {}
