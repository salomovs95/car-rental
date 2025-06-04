package com.salomovs.carrental.controller;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.salomovs.carrental.db.entity.Vehicle;
import com.salomovs.carrental.service.VehicleService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VehicleController implements Controller {
  private final VehicleService vehicleService;

  @Override
  public void handle(Scanner scanner) {
    System.out.println("Which service you need?");
    System.out.println("Register a new vehicle (R)");
    System.out.println("Update an existing vehicle (U)");
    System.out.println("Retrieve available vehicles (L)");
    System.out.print("Select an option: ");
    char option = scanner.nextLine()
                         .toUpperCase()
                         .charAt(0);

    switch(option) {
      case 'R':
        registerVehicle(scanner);
        break;
      case 'L':
        listAvailableVehicles(scanner);
        break;
      case 'U':
        updateVehicle(scanner);
        break;
      default:
        System.out.println("Invalid Option!");
        break;
    }
  }

  public void registerVehicle(Scanner scanner) {
    System.out.print("Vehicle's model: ");
    String model = scanner.nextLine();
    System.out.print("Vehicle's brand: ");
    String brand = scanner.nextLine();
    System.out.print("Daily price: ");
    Integer dailyPrice = Integer.valueOf(scanner.nextLine());
    System.out.print("Hour price: ");
    Integer hourPrice = Integer.valueOf(scanner.nextLine());
    System.out.print("Vehicle's plate number: ");
    String plateNumber = scanner.nextLine();
    System.out.print("Vehicle's plate country: ");
    String country = scanner.nextLine();

    Integer vehicleId = vehicleService.registerVehicle(model, brand, dailyPrice, hourPrice, plateNumber, country);
    System.out.println("Vehicle registered under ID: " + vehicleId);
  }

  public void listAvailableVehicles(Scanner scanner) {
    List<Vehicle> vehicles = vehicleService.listAvailableVehicles();
    vehicles.stream().forEach(System.out::println);
  }

  public void updateVehicle(Scanner scanner) {
    System.out.print("Vehicle's ID: ");
    Integer vehicleId = Integer.valueOf(scanner.nextLine());
    System.out.print("Vehicle's model: ");
    Optional<String> model = Optional.of(scanner.nextLine());
    System.out.print("Vehicle's brand: ");
    Optional<String> brand = Optional.of(scanner.nextLine());
    System.out.print("Vehicle's daily price: ");
    Optional<Integer> dailyPrice = Optional.of(Integer.valueOf(scanner.nextLine()));
    System.out.print("Vehicle's hour price: ");
    Optional<Integer> hourPrice = Optional.of(Integer.valueOf(scanner.nextLine()));
    System.out.print("Vehicle's plate number: ");
    Optional<String> plateNumber = Optional.of(scanner.nextLine());
    System.out.print("Vehicle's plate country: ");
    Optional<String> country = Optional.of(scanner.nextLine());

    vehicleService.updateVehicle(vehicleId, model, brand, plateNumber, country, dailyPrice, hourPrice);
    System.out.println("Vehicle updated! ID" + vehicleId);
  }
}
