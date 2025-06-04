package com.salomovs.carrental.db.entity;

public class Plate {
  private String value;
  private String country;

  public Plate(String value, String country) {
    this.value = value;
    this.country = country;
  }

  public String getValue() { return value; }
  public String getCountry() { return country; }

  public void setValue(String value) { this.value = value; }
  public void setCountry(String country) { this.country = country; }
}
