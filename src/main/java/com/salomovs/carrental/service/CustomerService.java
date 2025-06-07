package com.salomovs.carrental.service;

import java.util.Optional;

import com.salomovs.carrental.db.entity.Customer;
import com.salomovs.carrental.db.repository.Repository;
import com.salomovs.carrental.exception.CustomerNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerService {
  private Repository<Customer> ctRepo;

  public Integer saveCustomer(String taxId,
                              String fullName,
                              String email,
                              String phone) {
    Customer customer = new Customer(null, taxId, fullName, email, phone);
    return ctRepo.save(customer);
  }

  public void saveCustomer(Integer id,
                           Optional<String> taxId,
                           Optional<String> fullName,
                           Optional<String> email,
                           Optional<String> phone) {
    Customer customer = ctRepo.findById(id)
                              .orElseThrow(CustomerNotFoundException::new);

    if (taxId.isPresent()) customer.setTaxId(taxId.get());
    if (fullName.isPresent()) customer.setFullName(fullName.get());
    if (email.isPresent()) customer.setEmail(email.get());
    if (phone.isPresent()) customer.setPhone(phone.get());

    ctRepo.update(customer);
  }
}
