package com.rp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.rp.model.ReceiptDetail;

// This will be AUTO IMPLEMENTED by Spring into a Bean called receiptDetailRepository
// CRUD refers Create, Read, Update, Delete

public interface ReceiptDetailRepo extends CrudRepository<ReceiptDetail, Long> {

	@Query("select rh from ReceiptDetail rh where rh.id = :id")
	List<ReceiptDetail> findById(@Param("id") int id);
	
//	@Query("select rh from ReceiptDetail rh where rh.paymentType = :pt")
//	List<ReceiptDetail> findByPT(@Param("pt") String pt);
}
