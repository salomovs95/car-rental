package com.salomovs.carrental.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(name="tbl_customers")
@Getter @Setter @AllArgsConstructor
public class Customer {
  @Id @GeneratedValue(strategy=GenerationType.UUID)
  private Integer id;

  @Column(unique=true)
  private String taxId;

  private String fullName;

  @Column(unique=true)
  private String email;

  @Column(unique=true)
  private String phone;
}
