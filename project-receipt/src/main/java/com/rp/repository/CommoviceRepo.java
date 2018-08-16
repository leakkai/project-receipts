package com.rp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rp.model.Commovice;

// This will be AUTO IMPLEMENTED by Spring into a Bean called CommoRepo
// CRUD refers Create, Read, Update, Delete
@Repository("commoviceRepo")
public interface CommoviceRepo extends CrudRepository<Commovice, Long> {

	@Query("select c from Commovice c where c.commoviceId = :id")
	List<Commovice> findById(@Param("id") int id);
	
	@Query("select c.commoviceId from Commovice c where c.name = :name")
	Integer getIdByName(@Param("name") String name);
	
	@Query("select c from Commovice c where c.storeId = :id")
	List<Commovice> findByStoreId(@Param("id") int id);
}
