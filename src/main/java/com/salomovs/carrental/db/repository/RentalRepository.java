package com.salomovs.carrental.db.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.salomovs.carrental.db.DbClient;
import com.salomovs.carrental.db.orm.ORM;
import com.salomovs.carrental.db.entity.Rental;

public class RentalRepository implements Repository<Rental> {
  private final String FILENAME = "/home/salomovs95/docs/rentals.csv";
  private final String FILEHEADER = "id,vehicle_id,customer_id,rent_at,return_at,amount_to_pay";

  private ORM<Rental> orm;
  private List<Rental> rentals;

  public RentalRepository(ORM<Rental> orm) {
    this.orm = orm;
    this.rentals = DbClient.read(FILENAME, FILEHEADER)
            .stream()
            .map(orm::parseString)
            .collect(Collectors.toList());
  }

  @Override
  public List<Rental> findAll() {
    return rentals;
  }
 
  @Override
  public Integer save(Rental payload) {
    Integer rentalId = generateId();
    payload.setId(rentalId);
    String stRental = orm.parse(payload);
    DbClient.write(FILENAME,stRental);
    return rentalId;
  }

  @Override
  public void update(Rental payload) {
    List<String> upData = rentals.stream().map(r->{
      if (r.getId().equals(payload.getId())) return orm.parse(payload);
      return orm.parse(r);
    }).collect(Collectors.toList());

    DbClient.write(FILENAME, FILEHEADER, upData);
  }

  @Override
  public Optional<Rental> findById(Integer id) {
    return rentals.stream()
                  .filter(r->r.getId().equals(id))
                  .findAny();
  }

  private Integer generateId() {
    return generateId(rentals);
  }
}
