package com.salomovs.carrental.db.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.salomovs.carrental.db.DbClient;
import com.salomovs.carrental.db.orm.ORM;
import com.salomovs.carrental.db.entity.Customer;

public class CustomerRepository implements Repository<Customer> {
  private final String FILENAME = "/home/salomovs95/docs/customers.csv";
  private final String FILEHEADER = "id,tax_id,full_name,email,phone";

  private ORM<Customer> orm;
  private List<Customer> customers;

  public CustomerRepository(ORM<Customer> orm) {
    this.orm = orm;
    customers = DbClient.read(FILENAME, FILEHEADER)
                        .stream()
                        .map(orm::parseString)
                        .collect(Collectors.toList());
  }

  @Override
  public List<Customer> findAll() {
    return customers;
  }

  @Override
  public Integer save(Customer payload) {
    int ctId = generateId();
    payload.setId(ctId);
    String rawData = orm.parse(payload);
    DbClient.write(FILENAME, rawData);
    return ctId;
  }

  @Override
  public void update(Customer payload) {
    List<String> updatedData = customers.stream().map(c->{
      if (c.getId().equals(payload.getId())) return orm.parse(payload);
      return orm.parse(c);
    }).collect(Collectors.toList());

    DbClient.write(FILENAME, FILEHEADER, updatedData);
    payload.getId();
  }

  @Override
  public Optional<Customer> findById(Integer id) {
    if (customers == null) return Optional.empty();

    return customers.stream()
                    .filter(c->c.getId().equals(id))
                    .findAny();
  }

  private Integer generateId() {
    return this.generateId(customers);
  }
}
