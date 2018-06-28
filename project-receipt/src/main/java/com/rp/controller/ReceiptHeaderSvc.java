package com.rp.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	private ReceiptDetailSvc rdSvc;
	
	@Autowired
	private ReceiptHeaderRepo rhRepo;
	
	public Integer processHeader(RequestClass header) {
		
		Integer headerId = null;
		
		try {
			
			LocalDateTime receiptDate = header.getDate();
	        String storeName = header.getStoreName();
	        String paymentType = header.getPaymentType();
	        
	        BigDecimal total = header.getTotal();
	        BigDecimal tax = header.getTotalTax();
	        BigDecimal tips = header.getTips();
	        BigDecimal grandTotal = header.getGrandTotal();
	        
	        int addId = 0;
	        
	        if (null!= header.getAddressDummyText() && !header.getAddressDummyText().isEmpty()) {
	        	addId = Integer.parseInt(header.getAddressDummyText().get(0));
	        }
	        else {
	        	//Address Id not sent?!
	        	return -1;
	        }
	        
			//find exact store & address
			List<Store> storeList = stSvc.findByName(storeName);

			List<Address> addList = addSvc.findById(addId);
			
//			Address add = null;
			
			if (null != addList && addList.size() == 1) {
//				add = addList.get(0);
			}
			else if (addList.size() > 1) {
				//duplicate address error!!
				return -1;
			}

			int storeId = 0;
			
			if (storeList != null && storeList.size() > 0) {			
				//Get store id

//				addId = add.getAddressId();
				for (Store eachStore : storeList) {
					if (eachStore.getAddressId() == addId) {
						//found
						storeId = eachStore.getStoreId();
						break;
					}
				}
				
				if (storeId == 0) {
					//Error! Address not found for this store :(
					return -1;
				}
			}
			else {
				//Error! Should have created after address creation
				return -1;
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
	
	@Transactional(rollbackFor = Exception.class)
	public void processTransaction(@Valid RequestClass request) throws Exception {     

		Integer headerId = this.processHeader(request);

		if (null == headerId || headerId < 1) {
			//error creating or smtg
			throw new Exception("error creating header");
		}
		else {
			rdSvc.processDetail(request, headerId);
		}
	}
}
