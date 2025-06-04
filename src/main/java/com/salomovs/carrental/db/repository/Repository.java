package com.salomovs.carrental.db.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends Object> {
  List<T> findAll();
  Optional<T> findById(Integer id);
  Integer save(T payload);
  void update(T payload);

  default Integer generateId(List<T> collection) {
    return collection.size()+1;
  }
}
