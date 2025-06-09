package com.salomovs.carrental.service;

import java.util.List;

import com.salomovs.carrental.model.entity.Plate;
import com.salomovs.carrental.model.entity.Vehicle;
import com.salomovs.carrental.model.repository.VehicleRepository;
import com.salomovs.carrental.dto.RegisterVehicleDto;
import com.salomovs.carrental.dto.UpdateVehicleDto;
import com.salomovs.carrental.exception.VehicleNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VehicleService {
  private VehicleRepository vhRepo;

  public Integer registerVehicle(RegisterVehicleDto dto) {
    Plate plate = new Plate(dto.plate(), dto.country());
    Vehicle vehicle = new Vehicle(null, dto.model(), dto.brand(), dto.dailyPrice(), dto.hourPrice(), plate);
    return vhRepo.save(vehicle).getId();
  }

  public void updateVehicle(Integer id, UpdateVehicleDto dto) {
    Vehicle vehicle = vhRepo.findById(id)
                            .orElseThrow(VehicleNotFoundException::new);

    Plate plate = vehicle.getPlate();

    plate.setValue(dto.plate().orElse(plate.getValue()));
    plate.setCountry(dto.country().orElse(plate.getCountry()));
    
    vehicle.setPlate(plate);
    vehicle.setModel(dto.model().orElse(vehicle.getModel()));
    vehicle.setBrand(dto.brand().orElse(vehicle.getBrand()));
    vehicle.setDailyPrice(dto.dailyPrice().orElse(vehicle.getDailyPrice()));
    vehicle.setHourPrice(dto.hourPrice().orElse(vehicle.getHourPrice()));

    vhRepo.save(vehicle);
  }

  public Vehicle findVehicle(Integer vehicleId) {
    Vehicle vehicle = null;
    return vehicle;
  }

  public List<Vehicle> listAvailableVehicles() {
    return vhRepo.findAll();
  }
}
