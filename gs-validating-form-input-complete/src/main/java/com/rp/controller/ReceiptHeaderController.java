package com.rp.controller;

import java.text.ParseException;
import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rp.model.RequestDetail;
import com.rp.model.RequestHeader;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/receipt/") // This means URL's start with /user (after Application path)
public class ReceiptHeaderController {

	@Autowired// This means to let Spring auto create the bean
    // Which is auto-generated by Spring, we will use it to handle the data
	ReceiptHeaderSvc rhSvc;
	
	@Autowired
	ReceiptDetailSvc rdSvc;
	
    @GetMapping("")
    public String simpleView(Model model) {
    	model.addAttribute("req", new RequestHeader());
    	return "/receipt/header";
    }
    
    @GetMapping("/detail")
    public String simpleDetail(Model model) {
    	model.addAttribute("detail", new RequestDetail());
    	return "/receipt/detail";
    }
    
	@PostMapping("processHeader")
    public String addReceiptHeader(@Valid RequestHeader req, BindingResult bindingResult, Model model) throws ParseException {

        if (bindingResult.hasErrors()) {
            return "rh";
        }
        
        LocalDateTime receiptDate = req.getDate();
        String storeName = req.getName();
        String street = req.getStreet();
        String city = req.getCity();
        String zipCode = req.getCode();
        String state = req.getState();
        String country = req.getCountry();
    	
        //validate all req fields
        if (null != req) {
        	if (null == receiptDate || null == storeName || null == street || null == city || null == zipCode || null == state || null == country) {
        		//gg error
        		return "rh";
        	}
        }
        
        
        
        
        rhSvc.processHeader(receiptDate, storeName, street, city, zipCode, state, country);
        
        
//        ReceiptHeader r = new ReceiptHeader();
//        r.setDate(req.getDate());
//        r.setStoreId(123);
//        r.setAmount(new BigDecimal(123));
//        r.setPaymentType("cingcai");
//        r.setCreatedDate(LocalDateTime.now());
//        r.setLastModDate(LocalDateTime.now());
//        
//
//        
//        
//        
//        
//        rhRepo.save(r);
        
        
        //1) Need to really take date from UI
        
        
//		model.addAttribute("isInsert", true);
//        return "redirect:/results";
//        return "forward:/results";
//        return "results";
        return "/receipt/header";
    }
    
	@PostMapping("processDetail")
    public String addReceiptDetail(@Valid RequestDetail detail, BindingResult bindingResult, Model model) throws ParseException {

        if (bindingResult.hasErrors() || null == detail) {
            return "rh";
        }   

        rdSvc.processDetail(detail);
        
//        return "/receipt/detail";
        return this.simpleDetail(model);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    /*
	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		User n = new User();
		n.setName(name);
		n.setEmail(email);
		userRepository.save(n);
		return "Saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
	
	@GetMapping(path="/all")
	public String getAllUsers(Model model) {
		// This returns a JSON or XML with the users
//		Iterable<User> uList = userRepository.findAll();
		List<User> uuList = (List<User>) userRepository.findAll();
		model.addAttribute("users", uuList);
		
		return "/users/all";
	}
    
	@GetMapping(path="/all/deleteUser/{id}")
	public String deleteUser(@PathVariable int id, Model model) {

//		long iid = (long)id;
		List<User> uL = userRepository.findById(id);
//		User u = userRepository.findUserByName("dee2");

		if (null != uL && uL.size() == 1) {
			User u = uL.get(0);
			userRepository.delete(u);
		}
		
		
//		model.addAttribute("users", uuList);
		

		this.getAllUsers(model);
		
		return "redirect:/users/all";
	}
	
	@GetMapping(path="/search/id")
	public String searchById(@RequestParam(value="id")int id, Model model, User user) {
		
		List<User> uL = userRepository.findById(id);
		
		if (null != uL && uL.size() == 1) {
			model.addAttribute("isFound", true);
			model.addAttribute("resultUList", uL);
		}
		else {
			model.addAttribute("isFound", false);
		}
		
		return "/users/index";
	}
	
	@GetMapping(path="/search/name")
	public String searchByName(@RequestParam(value="name")String name, Model model, User user) {
		
		List<User> uList = userRepository.findByName(name);
		
		if (null != uList && uList.size() > 0) {
			model.addAttribute("isFound", true);
			model.addAttribute("resultUList", uList);
		}
		else {
			model.addAttribute("isFound", false);
		}
		
		return "/users/index";
	}
	
	@GetMapping(path="/search/email")
	public String searchByEmail(@RequestParam(value="email")String email, Model model, User user) {
		
		List<User> uList = userRepository.findByEmail(email);
		
		if (null != uList && uList.size() > 0) {
			model.addAttribute("isFound", true);
			model.addAttribute("resultUList", uList);
		}
		else {
			model.addAttribute("isFound", false);
		}
		
		return "/users/index";
	}
	
    @GetMapping(value = "/user/{id}")
    public String viewFindUser(@PathVariable int id, Model model) {
    	
    	List<User> uL = userRepository.findById(id);
		
		if (null != uL && uL.size() == 1) {
			model.addAttribute("resultU", uL.get(0));
		}
		
        return "/users/user";
    }
    
    @PostMapping("/user/update")
    public String updateUser(@Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "user";
        }
        
		userRepository.save(user);
        
		model.addAttribute("resultU", user);

        return "/users/user";
    }*/
}