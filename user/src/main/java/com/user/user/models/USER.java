/**
 * 
 */
package com.user.user.models;

import java.util.*;

/**
 * @author memory
 *
 */
public class USER {
	private Integer id;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private String phone;
	private Date dareOfBirth;
	/**
	 * 
	 */
	public USER() {
		
	}
	public USER(String userName, String firstName, String lastName) {
		// TODO Auto-generated constructor stub
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public String getUserName() {
		return this.userName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getDareOfBirth() {
		return dareOfBirth;
	}
	public void setDareOfBirth(Date dareOfBirth) {
		this.dareOfBirth = dareOfBirth;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
