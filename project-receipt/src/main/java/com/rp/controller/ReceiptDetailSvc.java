package com.rp.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rp.model.ReceiptDetail;
import com.rp.model.RequestClass;
import com.rp.model.RequestDetail;
import com.rp.repository.CommoviceRepo;
import com.rp.repository.ReceiptDetailRepo;

@Service
public class ReceiptDetailSvc {
		
	@Autowired
	private CommoviceSvc cmSvc;
//	
//	@Autowired
//	private AddressSvc addSvc;
	
//	@Autowired
//	private ReceiptHeaderRepo rhRepo;
	
	@Autowired
	private CommoviceRepo cmRepo;
	
	@Autowired
	private ReceiptDetailRepo rdRepo;
	
	/*public void processHeader(LocalDateTime receiptDate, String storeName, String street, String city, String postalCode, String state, String country) {
		
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
	}*/
	
	public void processDetail(RequestClass detail, Integer headerId) {
		
		
        List<String> nameList = detail.getName();
        List<Integer> qtyList = detail.getQuantity();
        List<BigDecimal> upList = detail.getUnitPrice();
        List<BigDecimal> priceList = detail.getPrice();
        List<BigDecimal> taxList = detail.getTax();
        List<String> category = detail.getCategory();
        //Still considering if I want to add this everytime from user...an item cannot be change into another tag that easily...hmmm....
        List<String> tag = detail.getTag();
        
        int rowNum = nameList.size();
        
        List<ReceiptDetail> rdArray = new ArrayList<ReceiptDetail>();
        LocalDateTime current = LocalDateTime.now();
        
        for (int i = 0; i < rowNum; i++) {
        	
        	String name = nameList.get(i);
        	
        	//Some item might have duplicate name..need to have more accurate fetching method
        	Integer comId = cmRepo.getIdByName(name);
        	
        	if (null == comId || comId < 1) {
        		if (!tag.isEmpty() && null != tag.get(i)) {
        			comId = cmSvc.createWithTag(name, tag.get(i));
        		}
        		else {
        			comId = cmSvc.create(name);
        		}
        	}
        	
        	ReceiptDetail newRD = new ReceiptDetail();
        	newRD.setTransactionId(headerId);
        	newRD.setCommoviceId(comId);
        	newRD.setCreatedDate(current);
        	newRD.setLastModDate(current);
        	
        	newRD.setPrice(priceList.get(i));
        	newRD.setQuantity(qtyList.get(i));

        	
        	if (taxList.isEmpty() || null == taxList.get(i)) {
        		newRD.setTax(new BigDecimal(0));
        	}
        	else {
        		newRD.setTax(taxList.get(i));
        	}
        	
        	if (!category.isEmpty() && null != category.get(i)) {
        		newRD.setCategory(category.get(i));
        	}
        	
        	newRD.setUnitPrice(upList.get(i));
        	
        	rdArray.add(newRD);
        }
		
		if (rdArray.size() > 0) {
			rdRepo.save(rdArray);
		}
	}
}
