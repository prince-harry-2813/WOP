package com.plainid.assignment.dao;

import java.util.List;


/**
 * Generic interface with basic CRUD methods to implement in DAO's
 * @param <T> Object Type
 * @param <K> Object key Parameter
 */
public interface IDAO <T,K> {


  T get (K id);

   List<T> getAll();

   void save(T t);

   void update(T... t);

   void delete(T t);
}
