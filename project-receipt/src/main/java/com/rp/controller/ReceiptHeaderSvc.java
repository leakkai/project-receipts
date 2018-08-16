package com.rp.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rp.model.ReceiptHeader;
import com.rp.model.RequestClass;
import com.rp.model.Store;
import com.rp.repository.ReceiptHeaderRepo;

@Service
public class ReceiptHeaderSvc extends BaseSvc {
		
	@Autowired
	private StoreSvc stSvc;
	
	@Autowired
	private ReceiptDetailSvc rdSvc;
	
	@Autowired
	private ReceiptHeaderRepo rhRepo;
	
	public ReceiptHeader processHeader(RequestClass header) {

		String dateTime = header.getDate();

		if (null == dateTime || dateTime.isEmpty()) {
			this.throwError("Date time cannot be empty");
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		LocalDateTime receiptDate = LocalDateTime.parse(dateTime, formatter);
        String storeName = header.getStoreName();
        String paymentType = header.getPaymentType();
        
        BigDecimal total = header.getTotal();
        BigDecimal tax = header.getTotalTax();
        BigDecimal tips = header.getTips();
        BigDecimal grandTotal = header.getGrandTotal();
        
        Integer addId = header.getAddressId();
        
        if (null == addId || addId < 1) {
        	this.throwError("Address ID cannot be empty");
        }
        
		//find exact store & address
		Store storeList = stSvc.findStore(storeName);
//		if (null == storeList || storeList.isEmpty() || storeList.size() < 1) {
//			this.throwError("Store not found");
//		}

		Integer storeId = 0;

//		for (Store eachStore : storeList) {
//			/*if (eachStore.getAddressId() == addId) {
//				//found
//				storeId = eachStore.getStoreId();
//				break;
//			}*/
//		}
		
		if (storeId == 0) {
			this.throwError("Address not found for: " + storeName);
		}
		
		
		if (null == tax) {
			tax = new BigDecimal(0);
		}
		
		if (null == tips) {
			tips = new BigDecimal(0);
		}
		
			
		ReceiptHeader newRH = new ReceiptHeader();
		LocalDateTime current = LocalDateTime.now();
		
		newRH.setDate(receiptDate);
		newRH.setTotal(total);
		newRH.setGrandTotal(grandTotal);
		newRH.setGrandTax(tax);
		newRH.setTips(tips);
		newRH.setPaymentType(paymentType);
		newRH.setStoreId(storeId);
		newRH.setCreatedDate(current);
		newRH.setLastModDate(current);
		
		rhRepo.save(newRH);

		return newRH;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public String processTransaction(@Valid RequestClass request) {
		
		//process header information
		ReceiptHeader header = this.processHeader(request);
		
		if (null == header) {
			this.throwError("Header creation failed");
		}
		
		if (null == header.getTransactionId() || header.getTransactionId() < 1) {
			this.throwError("Header ID can't be created?!");
		}
		
		//then detail
		rdSvc.processDetail(request, header);
		
		return "success";

	}
}
