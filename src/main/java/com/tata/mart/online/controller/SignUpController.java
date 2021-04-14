package com.tata.mart.online.controller;

import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tata.mart.online.model.User;
import com.tata.mart.online.service.UserServices.UserService;

@RestController
@RequestMapping("api/signup")
public class SignUpController {	
	
	 @Autowired
	 UserService userservice;
	 @RequestMapping("/user")
	 public ResponseEntity<?> SignUpUser(@RequestBody HashMap<String,String> signUpRequest){
		 try {
			 //JSONObject obj = new JSONObject();
			 User user = userservice.signUpUser(signUpRequest);
			 return ResponseEntity.ok(user);
			 //return new ResponseEntity<String>(obj.toString(),HttpStatus.OK);
		 }catch(Exception e) {
		 }
		 return null;
	 }
}


