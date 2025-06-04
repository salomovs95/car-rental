package com.salomovs.carrental.db.entity;

public class Customer {
  private Integer id;
  private String taxId;
  private String fullName;
  private String email;
  private String phone;

  public Customer(Integer id,
                  String taxId,
                  String fullName,
                  String email,
                  String phone) {
    this.id = id;
    this.taxId = taxId;
    this.fullName = fullName;
    this.email = email;
    this.phone = phone;
  }

  public Integer getId() { return id; }
  public String getTaxId() { return taxId; }
  public String getEmail() { return email; }
  public String getPhone() { return phone; }
  public String getFullName() { return fullName; }

  public void setId(Integer id) { this.id = id; }
  public void setTaxId(String taxId) { this.taxId = taxId; }
  public void setEmail(String email) { this.email = email; }
  public void setPhone(String phone) { this.phone = phone; }
  public void setFullName(String fullName) { this.fullName = fullName; }

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
