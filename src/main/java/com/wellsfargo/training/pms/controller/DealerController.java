package com.wellsfargo.training.pms.controller;


import com.wellsfargo.training.pms.Model.DealerAndAddressProjection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.pms.Model.Address;
import com.wellsfargo.training.pms.Model.Dealer;
import com.wellsfargo.training.pms.exception.ResourceNotFoundException;
import com.wellsfargo.training.pms.service.DealerService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api")
public class DealerController {
	@Autowired
	private DealerService dservice;
	
	 @PostMapping("/register")
		public ResponseEntity<String> createDealer(@Validated @RequestBody Dealer dealer) {
		try {	
		 Address address = dealer.getAddress();
			
			 // Establish the bi-directional relationship
	        address.setDealer(dealer);
	        dealer.setAddress(address);
					
			Dealer registeredDealer = dservice.registerDealer(dealer);
		        if (registeredDealer!= null) {
		            return ResponseEntity.ok("Registration successful");
		        } else {
		            return ResponseEntity.badRequest().body("Registration failed");
		        }
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An Error Occurred: "+e.getMessage());
					
		}
	 }
	
	//Open Postman and make POST request - http://localhost:8085/pms/api/login
			@PostMapping("/login")
			public Boolean loginDealer(@Validated @RequestBody Dealer dealer) throws ResourceNotFoundException
			{
				Boolean userExist=false;
				String email=dealer.getEmail();
				String password=dealer.getPassword();
			
				Dealer d = dservice.loginDealer(email).orElseThrow(() ->
				new ResourceNotFoundException("Dealer not found for this id :: "));
				
				if(email.equals(d.getEmail()) && password.equals(d.getPassword()))
				{
					userExist=true;

				}
				return userExist;
			}
			
			@GetMapping("/dealers")
			public ResponseEntity<List<DealerAndAddressProjection>>getDealerInfo(){
				
				
				List<DealerAndAddressProjection> selectedFields=dservice.getDealerInfo();
				
				return ResponseEntity.ok(selectedFields);
			}
	


}
