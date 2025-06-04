package com.salomovs.carrental.db.orm;

import com.salomovs.carrental.db.entity.Customer;

public class CustomerORM implements ORM<Customer> {
  @Override
  public String parse(Customer entity) {
    StringBuilder sb = new StringBuilder();
    return sb.append(entity.getId() + ",")
             .append(entity.getTaxId() + ",")
             .append(entity.getFullName() + ",")
             .append(entity.getEmail() + ",")
             .append(entity.getPhone())
             .toString();
  }

  @Override
  public Customer parseString(String rawData) {
    String[] data = rawData.split(",");
    return new Customer(
      Integer.valueOf(data[0]),
      data[1],
      data[2],
      data[3],
      data[4]
    );
  }
}
