package com.wellsfargo.training.pms.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.pms.Model.Dealer;
import com.wellsfargo.training.pms.Model.DealerAndAddressProjection;
import com.wellsfargo.training.pms.repository.DealerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DealerService {
	
	
	@Autowired
	private DealerRepository drepo;
	
	public Dealer registerDealer(Dealer d) {
		return drepo.save(d);
	}
	
	
	  /*
		Optional is a container object used to contain not-null objects introduced in Java 8. 
		Optional object is used to represent null with absent value. 
		This class has various utility methods to facilitate code to handle values as ‘available’ or ‘not available’ instead of checking null values. */
	public Optional<Dealer> loginDealer(String Email)
	{
		return drepo.findByEmail(Email);
		
	}
	
	public List<DealerAndAddressProjection> getDealerInfo(){
		
		return drepo.findSelectedFieldsFromDealerAndAddress();
	}

}
