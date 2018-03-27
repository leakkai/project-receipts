package com.rp.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rp.model.Address;
import com.rp.model.ReceiptHeader;
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
	
	public void processHeader(LocalDateTime receiptDate, String storeName, String street, String city, String postalCode, String state, String country) {
		
		try {
			//find exact store & address
			List<Store> storeList = stSvc.findByName(storeName);

			List<Address> addList = addSvc.findExactAddress(street, city, postalCode, state, country);
			
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
					addId = addSvc.createAddress(street, city, postalCode, state, country);
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
					addId = addSvc.createAddress(street, city, postalCode, state, country);
				}
				
				if (addId == 0) {
					//address creation GG
				}
				else {
					storeId = stSvc.createStore(addId, storeName);	
				}
			}
			
			ReceiptHeader newRH = new ReceiptHeader();
			LocalDateTime current = LocalDateTime.now();
			newRH.setAmount(new BigDecimal(123));
			newRH.setDate(receiptDate);
			newRH.setPaymentType("cingcai");
			newRH.setStoreId(storeId);
			newRH.setCreatedDate(current);
			newRH.setLastModDate(current);
			
			rhRepo.save(newRH);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
