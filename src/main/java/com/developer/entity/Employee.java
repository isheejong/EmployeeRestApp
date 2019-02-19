/* Copyright © 2016 Oracle and/or its affiliates. All rights reserved. */

package com.developer.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Employee")
public class Employee {
	
	private static final long serialVersionUID = -5809782578272943999L;  
	
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String birthDate;
	private String title;
	private String department;
	
	public Employee() {
		super();
		id = 0;
		firstName = "";
		lastName = "";
		email = "";
		phone = "";
		birthDate = "";
		title = "";
		department = "";  
	}
   
	public Employee(long id, String firstName, String lastName, String email, String phone, String birthDate, String title, String department){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.birthDate = birthDate;
		this.title = title;
		this.department = department;
	}
  
	public long getId() {
		return this.id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}
  
	public String getEmail(){
		return this.email;
	}
  
	public String getPhone(){
		return this.phone;
	}

	public String getBirthDate() {
		return this.birthDate;
	}

	public String getTitle() {
		return this.title;
	} 
  
	public String getDepartment(){
		return this.department;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

  
	@Override
	public String toString(){
		return "ID: " + id 
				+ " First Name: " + firstName
				+ " Last Name: " + lastName
				+ " EMail: " + email
				+ " Phone: " + phone
				+ " Birth Date: " + birthDate
				+ " Title: " + title
				+ " Department: " + department;
	}
}
