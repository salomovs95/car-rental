package com.salomovs.carrental.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Customer {
  private Integer id;
  private String taxId;
  private String fullName;
  private String email;
  private String phone;

  @Override
  public String toString() {
    String strCustomer = """
    ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    ::  CUSTOMER  ::::::::::::::::::::::::::::::::  ID: %d  ::
    ::  Name: %s  ::::::::::::::::::::::::::::  Tax ID: %s  ::
    ::  Email: %s :::::::::::::::::::::::::::::  Phone: %s  ::
    ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    """;

    return String.format(strCustomer,
                         id,
                         fullName,
                         taxId,
                         email,
                         phone);
  }
}
