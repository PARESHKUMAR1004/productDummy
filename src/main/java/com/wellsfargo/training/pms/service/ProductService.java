package com.wellsfargo.training.pms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.pms.Model.Product;
import com.wellsfargo.training.pms.repository.ProductRepository;

import jakarta.transaction.Transactional;






/*
 * A service layer is an additional layer in an MVC application that 
 * mediates communication between a controller and repository layer. 
 * The service layer contains business logic. 
 * In particular, it contains validation logic. */

/* @Service annotation allows developers to add business functionalities.
 *  @Transactional annotation allows to manage Database transactions efficiently */
@Service
@Transactional
public class ProductService {


@Autowired   //Dependency Injection Loose coupling
private ProductRepository prepo;

public Product saveProduct(Product p){

		return prepo.save(p);

}
public List<Product> listAll(){
	return prepo.findAll();
	
}

public Optional<Product>getSingleProduct(long id){
	return prepo.findById(id);
}

public void deleteProduct(long id) {
	prepo.deleteById(id);
}





}
