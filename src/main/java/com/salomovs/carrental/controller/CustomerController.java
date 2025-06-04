package com.salomovs.carrental.controller;

import java.util.Optional;
import java.util.Scanner;

import com.salomovs.carrental.service.CustomerService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerController implements Controller {
  private final CustomerService customerService;

  @Override
  public void handle(Scanner scanner) {
    System.out.println("Which service you need?");
    System.out.println("Customer Registration (R)");
    System.out.println("Update Customer's Data (U)");
    System.out.print("Select an option: ");
    char option = scanner.nextLine()
                         .toUpperCase()
                         .charAt(0);

    switch (option) {
      case 'R':
        registerCustomer(scanner);
        break;
      case 'U':
        updateCustomer(scanner);
        break;
      default:
        System.out.println("Invalid Option!");
        break;
    }
  }

  public void registerCustomer(Scanner scanner) {
    System.out.print("Customer's tax ID: ");
    String taxId = scanner.nextLine();
    System.out.print("Customer's full name: ");
    String fullName = scanner.nextLine();
    System.out.print("Customer's email: ");
    String email = scanner.nextLine();
    System.out.print("Customer's phone number: ");
    String phone = scanner.nextLine();

    Integer customerId = customerService.saveCustomer(taxId, fullName, email, phone);
    System.out.println("Customer's registered under iD: " + customerId);
  }

  public void updateCustomer(Scanner scanner) {
    System.out.print("Customer's ID: ");
    Integer customerId = Integer.valueOf(scanner.nextLine());
    System.out.print("Customer's tax ID: ");
    Optional<String> taxId = Optional.of(scanner.nextLine());
    System.out.print("Customer's full name: ");
    Optional<String> fullName = Optional.of(scanner.nextLine());
    System.out.print("Customer's email: ");
    Optional<String> email = Optional.of(scanner.nextLine());
    System.out.print("Customer's phone number: ");
    Optional<String> phone = Optional.of(scanner.nextLine());

    customerService.saveCustomer(customerId, taxId, fullName, email, phone);
    System.out.println("Customer updated! ID " + customerId);
  }
}
