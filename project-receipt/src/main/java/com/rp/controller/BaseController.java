package com.rp.controller;

import java.util.ArrayList;
import java.util.List;

import ResponseHolder.ResponseHolder;

public class BaseController {

	public BaseController() {
	}

	public ResponseHolder buildResponse(String e) {
		ResponseHolder response = new ResponseHolder();
		
		if (null != e && !e.isEmpty()) {
			response.setStatus(e);
		}
		else {
			response.setStatus("success");
		}
		
		return response;
	}
	
	public ResponseHolder buildResponse(List<?> obj) {
		ResponseHolder response = new ResponseHolder();
		
		response.setStatus("success");
		response.setObject(obj);
		
		return response;
	}
	
	public ResponseHolder buildResponse(Object obj) {
		ResponseHolder response = new ResponseHolder();
		
		List<Object> objList = new ArrayList<Object>();
		objList.add(obj);
		
		response.setStatus("success");
		response.setObject(objList);
		
		return response;
	}
}
