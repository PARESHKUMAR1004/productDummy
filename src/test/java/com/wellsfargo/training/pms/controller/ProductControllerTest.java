package com.wellsfargo.training.pms.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wellsfargo.training.pms.Model.Product;
import com.wellsfargo.training.pms.exception.ResourceNotFoundException;
import com.wellsfargo.training.pms.repository.ProductRepository;
import com.wellsfargo.training.pms.service.ProductService;


@SpringBootTest
class ProductControllerTest {

//	@MockBean
//	ProductRepository prepo; //Create Mock object of Product Repository
//	
	@MockBean
	private ProductService pservice;
	
	
	@Autowired
	private ProductController productController;
	
	
	Product product=new Product();
	
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSaveProduct() {
		
		product.setName("Optical Mouse");
		product.setBrand("Logitech");
		product.setMadein("FinLand");
		
		product.setPrice(500.00f);
		
		when(pservice.saveProduct(any(Product.class))).thenReturn(product);
		
		Product re=productController.saveProduct(product);
		
//		assertEquals(HttpStatus.CREATED, re.getStatusCode());
//		assertEquals("Optical Mouse",re.getBody().getName());
//		assertEquals("Optical Mouse",re.getBody().getName());
//		assertEquals("Optical Mouse",re.getBody().getName());
		
		assertEquals(re,product);
		verify(pservice,times(1)).saveProduct(any(Product.class));
		
		
	}

	@Test
	void testGetAllProducts() {
		
		List<Product> mockProducts=new ArrayList<>();
		mockProducts.add(new Product(1L,"Pen","Reynolds","India",20.0f));
		mockProducts.add(new Product(2L,"HDD","Seagate","India",5000.0f));
		
		when(pservice.listAll()).thenReturn(mockProducts);
		List<Product> responseProducts=productController.getAllProducts();
		
		assertEquals(2,responseProducts.size());
		assertEquals("Pen",responseProducts.get(0).getName());
		
		assertEquals("HDD", responseProducts.get(1).getName());
		
		verify(pservice,times(1)).listAll()
;		
		
		
		
	}

	@Test
	void testGetProductById() throws ResourceNotFoundException {
		Product mockProduct=new Product(2L,"HDD","Seagate","India",500.00f);
		when(pservice.getSingleProduct(2L)).thenReturn(Optional.of(mockProduct));
		
		ResponseEntity<Product>re=productController.getProductById(2L);
		assertEquals(HttpStatus.OK,re.getStatusCode());
		assertEquals("HDD",re.getBody().getName());
		assertEquals("Seagate",re.getBody().getBrand());
		assertEquals("India",re.getBody().getMadein());
		assertEquals(500.0,re.getBody().getPrice());
		
		
		
		verify(pservice,times(1)).getSingleProduct(2L);
	}

	@Test
	void testUpdateProduct() throws ResourceNotFoundException{
		
		Product exisitingProduct=new Product(2L,"HDD","Seagate","India",500.00f);
		Product updatedProduct=new Product(2L,"HDD","Seagate","USA",600.00f);
		
		when(pservice.getSingleProduct(2L)).thenReturn(Optional.of(exisitingProduct));
		
		when(pservice.saveProduct(any(Product.class))).thenReturn(updatedProduct);
		
		ResponseEntity<Product> re=productController.updateProduct(2L, updatedProduct);
		assertEquals(HttpStatus.OK,re.getStatusCode());
		assertEquals("HDD",re.getBody().getName());
		assertEquals("Seagate",re.getBody().getBrand());
		assertEquals("USA",re.getBody().getMadein());
		assertEquals(600.0,re.getBody().getPrice());
		
		verify(pservice,times(1)).getSingleProduct(2L);
		verify(pservice,times(1)).saveProduct(any(Product.class));
	}

	@Test
	void testDeleteProduct() throws ResourceNotFoundException {
		
		Product exisitingProduct=new Product(2L,"HDD","Seagate","India",500.00f);
		when(pservice.getSingleProduct(2L)).thenReturn(Optional.of(exisitingProduct));
		doNothing().when(pservice).deleteProduct(2L);
		
		Map<String,Boolean> response=productController.deleteProduct(2L);
		
		assertTrue(response.containsKey("Deleted"));
		assertTrue(response.get("Deleted"));
		
		verify(pservice,times(1)).getSingleProduct(2L);
		verify(pservice,times(1)).deleteProduct(2L);
		
		
	}

}
