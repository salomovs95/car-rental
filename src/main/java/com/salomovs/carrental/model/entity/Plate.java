package com.salomovs.carrental.model.entity;

import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter @AllArgsConstructor
public class Plate {
  private String value;
  private String country;
}
