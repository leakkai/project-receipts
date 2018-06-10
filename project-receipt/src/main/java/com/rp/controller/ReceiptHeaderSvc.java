package com.rp.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rp.model.Address;
import com.rp.model.ReceiptHeader;
import com.rp.model.RequestClass;
import com.rp.model.Store;
import com.rp.repository.ReceiptHeaderRepo;

@Service
public class ReceiptHeaderSvc {
		
	@Autowired
	private StoreSvc stSvc;
	
	@Autowired
	private AddressSvc addSvc;
	
	@Autowired
	private ReceiptHeaderRepo rhRepo;
	
	public Integer processHeader(RequestClass header) {
		
		Integer headerId = null;
		
		try {
			
			LocalDateTime receiptDate = header.getDate();
	        String storeName = header.getStoreName();
	        String street = header.getStreet();
	        String city = header.getCity();
	        String zipCode = header.getCode();
	        String state = header.getState();
	        String country = header.getCountry();
	        String paymentType = header.getPaymentType();
	        
	        BigDecimal total = header.getTotal();
	        BigDecimal tax = header.getTotalTax();
	        BigDecimal tips = header.getTips();
	        BigDecimal grandTotal = header.getGrandTotal();
	        
			//find exact store & address
			List<Store> storeList = stSvc.findByName(storeName);

			List<Address> addList = addSvc.findExactAddress(street, city, zipCode, state, country);
			
			Address add = null;
			
			int storeId = 0;
			
			if (null != addList && addList.size() == 1) {
				add = addList.get(0);
			}
			else if (addList.size() > 1) {
				//duplicate address error!!
			}
			
			if (storeList != null && storeList.size() > 0) {			
				//store profile exist, check if current address has any profile
				boolean hasAdd = false;
				int addId = 0;
				
				if (null == add) {
					//create address for this store
					addId = addSvc.createAddress(street, city, zipCode, state, country);
				}
				else {
					addId = add.getAddressId();
					for (Store eachStore : storeList) {
						if (eachStore.getAddressId() == addId) {
							//found
							hasAdd = true;
							storeId = eachStore.getStoreId();
						}
					}
				}
				
				if (!hasAdd) {
					//Address profile exist, but not one store has it. Meaning "new store". Create a whole new store
					storeId = stSvc.createStore(addId, storeName);
				}
			}
			else {
				//new store, usually mean new add
				int addId = 0;
				
				if (null == add) {
					//create address profile
					addId = addSvc.createAddress(street, city, zipCode, state, country);
				}
				
				if (addId == 0) {
					//address creation GG
				}
				else {
					storeId = stSvc.createStore(addId, storeName);	
				}
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

			headerId = newRH.getTransactionId();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return headerId;
	}
}
