package com.salomovs.carrental.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salomovs.carrental.model.entity.Customer;
import com.salomovs.carrental.model.repository.CustomerRepository;
import com.salomovs.carrental.dto.RegisterCustomerDto;
import com.salomovs.carrental.dto.UpdateCustomerDto;
import com.salomovs.carrental.exception.CustomerNotFoundException;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class CustomerService {
  private CustomerRepository ctRepo;

  public Integer saveCustomer(RegisterCustomerDto dto) {
    Customer customer = new Customer(null, dto.taxId(), dto.fullName(), dto.email(), dto.phone());
    return ctRepo.save(customer).getId();
  }

  public void saveCustomer(Integer id, UpdateCustomerDto dto) {
    Customer customer = ctRepo.findById(id)
                              .orElseThrow(CustomerNotFoundException::new);

    String email = dto.email().orElse(customer.getEmail());
    String fullName = dto.fullName().orElse(customer.getFullName());
    String phone = dto.phone().orElse(customer.getPhone());
    String taxId = dto.taxId().orElse(customer.getTaxId());

    customer.setTaxId(taxId);
    customer.setFullName(fullName);
    customer.setEmail(email);
    customer.setPhone(phone);

    ctRepo.save(customer);
  }

  public List<Customer> listCustomers() {
    return ctRepo.findAll();
  }
}
