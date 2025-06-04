package com.salomovs.carrental.db.orm;

public interface ORM<T extends Object> {
  T parseString(String rawData);
  String parse(T entity);
}
