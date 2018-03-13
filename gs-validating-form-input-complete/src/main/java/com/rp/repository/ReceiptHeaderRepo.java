package com.rp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.rp.model.ReceiptHeader;

// This will be AUTO IMPLEMENTED by Spring into a Bean called receiptHeaderRepository
// CRUD refers Create, Read, Update, Delete

public interface ReceiptHeaderRepo extends CrudRepository<ReceiptHeader, Long> {

	@Query("select rh from ReceiptHeader rh where rh.id = :id")
	List<ReceiptHeader> findById(@Param("id") int id);
}
