package com.rp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.rp.model.Address;

// This will be AUTO IMPLEMENTED by Spring into a Bean called AddressRepo
// CRUD refers Create, Read, Update, Delete

public interface AddressRepo extends CrudRepository<Address, Long> {

	@Query("select a from Address a where a.id = :id")
	List<Address> findById(@Param("id") int id);
	
	@Query("select a from Address a where a.country = :country and "
			+ "a.state = :state and a.postalCode = :code and "
			+ "a.city = :city and lower(a.address1) like lower(concat('%', :add1, '%'))")
	List<Address> findExact(@Param("country") String country, @Param("state") String state, @Param("code") String postalCode,
							@Param("city") String city, @Param("add1") String address1);
}
