package com.ecommerce.sb_ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="addresses")
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long addressId;
	
	@NotBlank
	@Size(min=5,message="Street name nust be atleast 5 characters")
	private String street;
	
	@NotBlank
	@Size(min=10,message="Building  name nust be atleast 5 characters")
	private String buildingName;
	
	@NotBlank
	@Size(min=4,message="City  name nust be atleast 5 characters")
	private String city;
	
	@NotBlank
	@Size(min=2,message="State  name nust be atleast 5 characters")
	private String state;
	
	@NotBlank
	@Size(min=2,message="Country  name nust be atleast 5 characters")
	private String country;
	
	@NotBlank
	@Size(min=6,message="Pin Code nust be atleast 5 characters")
	private String pinCode;

	@ManyToMany(mappedBy="addresses")
	private List<Users>users=new ArrayList<>();


	public Long getAddressId() {
		return addressId;
	}


	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getBuildingName() {
		return buildingName;
	}


	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getPinCode() {
		return pinCode;
	}


	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}


	public List<Users> getUsers() {
		return users;
	}


	public void setUsers(List<Users> users) {
		this.users = users;
	}


	public Address(@NotBlank @Size(min = 5, message = "Street name nust be atleast 5 characters") String street,
			@NotBlank @Size(min = 10, message = "Building  name nust be atleast 5 characters") String buildingName,
			@NotBlank @Size(min = 4, message = "City  name nust be atleast 5 characters") String city,
			@NotBlank @Size(min = 2, message = "State  name nust be atleast 5 characters") String state,
			@NotBlank @Size(min = 2, message = "Country  name nust be atleast 5 characters") String country,
			@NotBlank @Size(min = 6, message = "Pin Code nust be atleast 5 characters") String pinCode) {
		super();
		this.street = street;
		this.buildingName = buildingName;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pinCode = pinCode;
	}
	
	
	
	}
