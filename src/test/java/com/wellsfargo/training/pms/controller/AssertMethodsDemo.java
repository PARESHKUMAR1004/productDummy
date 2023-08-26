package com.wellsfargo.training.pms.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Assert Methods Demo")
class AssertMethodsDemo {
	
	
	private ArrayList<String> list;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		System.out.println("Using @BeforeAll, executed once before All tests");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
		System.out.println("Using @AfterAll, executed once after All tests");
	}

	@BeforeEach
	void setUp() throws Exception {
		
		System.out.println("Using @BeforeEach, executed before each tests");
		
		list=new ArrayList<>();
	}

	@AfterEach
	void tearDown() throws Exception {
		
		System.out.println("Using @AfterEach, executed after each tests");
		list.clear();
	}

	@Test
	void test() {
		list.add("Junit");
		
		assertFalse(list.isEmpty());
		assertEquals(1,list.size());
		System.out.println("In test method");
	}
	
	@Test
	void test1() {
		list.add("Junit");
		assertFalse(list.isEmpty());
		assertEquals(1,list.size());
		System.out.println("In test method");
	}

}
