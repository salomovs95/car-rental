package com.salomovs.carrental;

//import java.sql.Connection;
//import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import com.salomovs.carrental.controller.CustomerController;
import com.salomovs.carrental.controller.RentalController;
import com.salomovs.carrental.controller.VehicleController;
import com.salomovs.carrental.db.orm.CustomerORM;
import com.salomovs.carrental.db.orm.RentalORM;
import com.salomovs.carrental.db.orm.VehicleORM;
import com.salomovs.carrental.db.repository.CustomerRepository;
import com.salomovs.carrental.db.repository.RentalRepository;
import com.salomovs.carrental.db.repository.VehicleRepository;
import com.salomovs.carrental.service.BrazilTaxService;
import com.salomovs.carrental.service.CustomerService;
import com.salomovs.carrental.service.InvoiceService;
import com.salomovs.carrental.service.RentalService;
import com.salomovs.carrental.service.VehicleService;
import com.salomovs.carrental.service.types.TaxService;

public class App {
  private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  public static void main(String[] args) {
    /*
    String dbUrl = System.getenv("DATABASE_URL");

    try {
      Class.forName("org.postgresql.Driver");
    } catch (Exception e) {
      e.printStackTrace();
    }
    */

    TaxService tService = new BrazilTaxService();

    CustomerORM cOrm = new CustomerORM();
    CustomerRepository cRepo = new CustomerRepository(cOrm);
    CustomerService cService = new CustomerService(cRepo);
    CustomerController customerController = new CustomerController(cService);

    VehicleORM vOrm = new VehicleORM();
    VehicleRepository vRepo = new VehicleRepository(vOrm);
    VehicleService vService = new VehicleService(vRepo);
    VehicleController vehicleController = new VehicleController(vService);

    RentalORM rOrm = new RentalORM();
    RentalRepository rRepo = new RentalRepository(rOrm);
    RentalService rService = new RentalService(vRepo, rRepo);
    InvoiceService iService = new InvoiceService(tService, cRepo, vRepo, rRepo);
    RentalController rentalController = new RentalController(rService, iService);
 
    try (Scanner scanner = new Scanner(System.in) ) { //Connection conn = DriverManager.getConnection(String.format("%s?user=postgres&password=postgres", dbUrl)); {
      char continues = 'Y';
      
      while(continues != 'N') {
        System.out.println(".: WELCOME TO CARRENTAL :.");
        System.out.println("Which concern you have:");
        System.out.println("Is it about CUSTOMER SUCCESS (C)");
        System.out.println("Is it about VEHICLES (V)");
        System.out.println("Is it about RENTALS (R)");
        System.out.print("Select an option: ");
        
        char option = scanner.nextLine()
                        .toUpperCase()
                        .charAt(0);

        switch(option) {
          case 'C':
            customerController.handle(scanner);
            break;
          case 'V':
            vehicleController.handle(scanner);
            break;
          case 'R':
            rentalController.handle(scanner);
            break;
          default:
            System.out.println("Invalid Option!");
            break;
        }

        System.out.print("Anything else? (Y/n) ");
        continues = scanner.nextLine()
                           .toUpperCase()
                           .charAt(0);
        
        if (continues == 'Y') clearScreen();
      }
    } catch (Exception e) {
      String error = String.format("%s [EXCEPTION] %s: %s",
                                   dtf.format(LocalDateTime.now()),
                                   e.getClass().getName(),
                                   e.getLocalizedMessage());
      System.out.println(error);
      e.printStackTrace();
    } finally {
      System.out.println("Comeback anytime! S2");
    }
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
