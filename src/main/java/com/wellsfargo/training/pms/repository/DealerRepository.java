package com.wellsfargo.training.pms.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wellsfargo.training.pms.Model.Dealer;
import com.wellsfargo.training.pms.Model.DealerAndAddressProjection;

public interface DealerRepository extends JpaRepository<Dealer, Long> {
	
	public Optional<Dealer> findByEmail(String email);
	
	
	/*Some time case arises, where we need a custom query to fulfil one test case like  you use more than 2-3 
	 * query parameters or need to define multiple joins to other entities. 
	* We can use @Query annotation to specify a query within a repository.
	* In these situations, you better use Spring Data JPA’s @Query annotation to 
	* specify a custom JPQL or native SQL query.
		* 
	*  */
		// Custom Queries	
	  
	  @Query("SELECT new com.wellsfargo.training.pms.Model.DealerAndAddressProjection"
				+ "(d.id, d.fname,d.lname,d.phoneNo,"
				+ "d.email, a.street, a.city,a.pincode) "
				+ "FROM Dealer d JOIN d.address a")
		List<DealerAndAddressProjection> findSelectedFieldsFromDealerAndAddress();
	  
	  

}
