package com.rp.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rp.model.Address;
import com.rp.model.AddressReq;
import com.rp.model.RequestClass;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/receipt/") // This means URL's start with /user (after Application path)
public class ReceiptHeaderController {

	@Autowired// This means to let Spring auto create the bean
    // Which is auto-generated by Spring, we will use it to handle the data
	ReceiptHeaderSvc rhSvc;
	
	@Autowired
	ReceiptDetailSvc rdSvc;
	
	@Autowired
	AddressSvc addSvc;
	
	@Autowired
	StoreSvc stoSvc;
	
    @GetMapping("")
    public String simpleView(Model model) {
    	model.addAttribute("request", new RequestClass());
//    	model.addAttribute("add", new Address());
    	return "/receipt/home";
    }
    
	@PostMapping("process")
    public String processTransaction(@Valid RequestClass request, BindingResult bindingResult, Model model) throws ParseException {

        if (bindingResult.hasErrors()) {
            return "rh";
        }        

        Integer headerId = rhSvc.processHeader(request);
        
        if (null == headerId) {
        	//error creating or smtg
        }
        else {
        	rdSvc.processDetail(request, headerId);
        }
        
//		model.addAttribute("isInsert", true);
//        return "redirect:/results";
//        return "forward:/results";
//        return "results";
        return this.simpleView(model);
    }
	
	
	@GetMapping("getAddress")
    public @ResponseBody RequestClass getAddress(@Valid RequestClass request, BindingResult bindingResult, Model model) throws ParseException {

        if (bindingResult.hasErrors()) {
//            return "rh";
        }        

        String name = request.getStoreName();
        
        if (null == name) {
//        	return "rh";
        }
        
        List<Address> storeAddress = addSvc.findByStore(name);
        
        if (storeAddress.isEmpty()) {
        	//error creating or smtg
        }
        
        List<String> addressDummy = addSvc.convertToTextArray(storeAddress);
        
        request.setAddressList(storeAddress);
        request.setAddressDummyText(addressDummy);

        return request;
    }
	
	@GetMapping("setupAddress")
	public @ResponseBody Address setupAddress(Model model) {
		model.addAttribute("add", new Address());
    	return new Address();
	}
	
	
	@RequestMapping(value = "addAddress", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody String addAddress(@RequestBody AddressReq request) {        

		String add1 = request.getStreet();
		String city = request.getCity();
		String zip = request.getZip();
		String sta = request.getState();
		String cou = request.getCountry();
		
		String name = request.getStoreName();
		
		if (null == add1 || add1.isEmpty() ||
				null == city || city.isEmpty() ||
				null == zip || zip.isEmpty() ||
				null == sta || sta.isEmpty() ||
				null == cou || cou.isEmpty() ||
				null == name || name.isEmpty()) {
			return "{\"status\":\"empty field(s)\"}";
		}
		
		int id = addSvc.createAddress(add1, city, zip, sta, cou);
		
		if (id < 1) {
			return "{\"status\":\"address fail\"}";
		}
		
		int stoId = stoSvc.createStore(id, name);
		
		if (stoId < 1) {
			return "{\"status\":\"store fail\"}";
		}
		
        return "{\"status\":\"success\", "
        		+ "\"id\":" + stoId + "}";
    }

}