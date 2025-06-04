package com.salomovs.carrental.db.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.salomovs.carrental.db.DbClient;
import com.salomovs.carrental.db.orm.ORM;
import com.salomovs.carrental.db.entity.Vehicle;

public class VehicleRepository implements Repository<Vehicle> {
  private final String FILENAME = "/home/salomovs95/docs/vehicles.csv";
  private final String FILEHEADER = "id,model,brand,daily_price,hour_price,plate_number,plate_country";

  private List<Vehicle> vehicles;
  private ORM<Vehicle> orm;

  public VehicleRepository(ORM<Vehicle> orm) {
    this.orm = orm;
    this.vehicles = DbClient.read(FILENAME, FILEHEADER)
                            .stream()
                            .map(orm::parseString)
                            .collect(Collectors.toList());
  }

  @Override
  public List<Vehicle> findAll() {
    return vehicles;
  }

  @Override
  public Integer save(Vehicle payload) {
    int vhId = generateId();
    payload.setId(vhId);
    String stPayload = orm.parse(payload);
    DbClient.write(FILENAME, stPayload);
    return vhId;
  }

  @Override
  public void update(Vehicle payload) {
    List<String> upData = vehicles.stream().map(v->{
      if (v.getId().equals(payload.getId())) return orm.parse(payload);
      return orm.parse(v);
    }).collect(Collectors.toList());

    DbClient.write(FILENAME, FILEHEADER, upData);
  }

  @Override
  public Optional<Vehicle> findById(Integer id) {
    if (vehicles == null) return Optional.empty();
    return vehicles.stream()
                   .filter(v->v.getId().equals(id))
                   .findAny();
  }

  public Integer generateId() {
    return this.generateId(vehicles);
  }
}
