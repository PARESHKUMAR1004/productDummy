package com.wellsfargo.training.pms.Model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class DealerAddress {
	
    private Long id;
	private String email;
	private String fname;
	private String lname;
	private String password;
	private Date dob;
	private String phoneNo;

	private String street;
	private String city;
	private int pincode;

}
