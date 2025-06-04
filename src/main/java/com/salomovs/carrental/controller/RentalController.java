package com.salomovs.carrental.controller;

import java.util.Scanner;

import com.salomovs.carrental.db.entity.Invoice;
import com.salomovs.carrental.service.InvoiceService;
import com.salomovs.carrental.service.RentalService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RentalController implements Controller {
  private final RentalService rentalService;
  private final InvoiceService invoiceService;

  @Override
  public void handle(Scanner scanner) {
    System.out.println("Which service you need?");
    System.out.println("Rent a vehicle (V)");
    System.out.println("Return a vehicle (R)");
    System.out.println("Retrieve info about a invoice (I)");
    System.out.println("List all invoices (L)");

    System.out.print("Select an option: ");
    char option = scanner.nextLine()
                         .toUpperCase()
                         .charAt(0);

    switch (option) {
      case 'V':
        rentVehicle(scanner);
        break;
      case 'R':
        returnVehicle(scanner);
        break;
      case 'I':
        retrieveInvoice(scanner);
        break;
      case 'L':
        listInvoicesByCustomer(scanner);
        break;
      default:
        System.out.println("Invalid Option!");
        break;
    }
  }

  public void rentVehicle(Scanner scanner) {
    System.out.print("Enter customer ID: ");
    Integer customerId = Integer.valueOf(scanner.nextLine());
    System.out.print("Enter vehicle ID: ");
    Integer vehicleId = Integer.valueOf(scanner.nextLine());

    rentalService.rentCar(customerId, vehicleId);
  }

  public void returnVehicle(Scanner scanner) {
    System.out.print("Enter rental ID: ");
    Integer rentalId = Integer.valueOf(scanner.nextLine());
    rentalService.returnCar(rentalId);
    System.out.println("Returned Successfully! 10Q :D");
  }

  public void retrieveInvoice(Scanner scanner) {
    System.out.print("Enter rental ID: ");
    Integer rentalId = Integer.valueOf(scanner.nextLine());
    Invoice invoice = invoiceService.processInvoice(rentalId);
    System.out.println(invoice);
  }

  public void listInvoicesByCustomer(Scanner scanner) {
    System.out.println("Feature Not Available Yet, Sorry! S2  ;C");
  }
}
