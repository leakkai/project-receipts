package com.rp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rp.model.Store;

// This will be AUTO IMPLEMENTED by Spring into a Bean called StoreRepo
// CRUD refers Create, Read, Update, Delete
@Repository("storeRepo")
public interface StoreRepo extends CrudRepository<Store, Long> {

	@Query("select s from Store s where s.id = :id")
	List<Store> findById(@Param("id") int id);
	
	@Query("select s from Store s where s.storeName = :name")
	Store findByName(@Param("name") String name);
}
