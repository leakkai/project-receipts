package com.rp.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rp.model.Commovice;
import com.rp.model.ReceiptDetail;
import com.rp.model.ReceiptHeader;
import com.rp.model.RequestClass;
import com.rp.repository.ReceiptDetailRepo;

@Service
public class ReceiptDetailSvc {
		
	@Autowired
	private CommoviceSvc cmSvc;
	
	@Autowired
	private ReceiptDetailRepo rdRepo;
	
	public void processDetail(RequestClass detail, ReceiptHeader header) {

        List<String> nameList = detail.getName();
        List<Integer> qtyList = detail.getQuantity();
        List<BigDecimal> upList = detail.getUnitPrice();
        List<BigDecimal> priceList = detail.getPrice();
        List<BigDecimal> taxList = detail.getTax();
        List<String> category = detail.getCategory();
        //Still considering if I want to add this everytime from user...an item cannot be change into another tag that easily...hmmm....
//        List<String> tag = detail.getTag();
        
        List<ReceiptDetail> rdArray = new ArrayList<ReceiptDetail>();
        LocalDateTime current = LocalDateTime.now();
        
        List<Commovice> commoList = cmSvc.getCommoviceByStore(header.getStoreId());
        
        for (int i = 0; i < nameList.size(); i++) {
        	
        	String name = nameList.get(i);
        	
        	//Some item might have duplicate name..need to have more accurate fetching method
//        	Integer comId = cmRepo.getIdByName(name);
        	Integer comId = null;
        	for (Commovice c : commoList) {
        		if (c.getName().equalsIgnoreCase(name)) {
        			comId = c.getCommoviceId();
        			break;
        		}
        	}
        	
        	if (null == comId || comId < 1) {
        		/*if (!tag.isEmpty() && null != tag.get(i)) {
        			comId = cmSvc.createWithTag(name, tag.get(i));
        		}
        		else {*/
        			comId = cmSvc.create(name, header.getStoreId());
//        		}
        	}
        	
        	
        	ReceiptDetail newRD = new ReceiptDetail();
        	newRD.setTransactionId(header.getTransactionId());
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
