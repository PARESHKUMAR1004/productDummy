package com.wellsfargo.training.pms.Model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;



/*
Lombok is an open-source library (basically a standalone jar) capable of doing magic in automating the boilerplate code generation for any java class. 
So if Lombok is in the classpath, it can quickly get rid of all the getters & setters methods, class constructors, hashcode and equals methods and many 
more by adding a couple of annotations to the class.
*/

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Address {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long addressid;
	private @NonNull String street;
	private @NonNull String city;
	private int pincode;
	
	
	
	/*
     * Modeling with foreign key relationship in JPA.
     * Place @OneToOne on the primary class entity field Dealer.
     * Place @JoinColumn to configure foreign key column dealer_id in the Address class that maps to the primary key
     * column of Dealer class. 
     */
	@OneToOne
	@JoinColumn(name="dealer_id")
	private Dealer dealer;

}
