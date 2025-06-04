package com.salomovs.carrental.db.entity;

public record Invoice(
  Rental rental,
  Customer customer,
  Vehicle vehicle,
  Integer taxes
) {
  public static Invoice generateInvoice(Customer customer, Vehicle vehicle, Rental rental, Double taxes) {
    Integer taxess = Integer.valueOf(String.valueOf(taxes).replace(".",""));
    return new Invoice(rental, customer, vehicle, taxess);
  }

  public String parse(Vehicle vehicle, Customer customer) {
    String strInvoice = """
    ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    ::  INVOICE :::::::::::::::::::::::::::  Rental ID: %d  ::
    ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    %s
    %s
    %s
    ::  Totals: $ %.2f  :::::::::::::::::::::::  Taxes: $ %.2f  ::
    ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    """;

    double finalPrice = (rental.getAmountToPay()+taxes)/100;
    return String.format(strInvoice,
                         rental().getId(),
                         customer(),
                         vehicle(),
                         rental(),
                         finalPrice,
                         taxes()/100);
  }
}
