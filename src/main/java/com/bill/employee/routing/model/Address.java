package com.bill.employee.routing.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="address")
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="address_id")
	private Long addressId;
	
	@OneToMany(mappedBy="address")
	@JsonIgnore
	private List<Employee> employees = new ArrayList<>();
	
	@Column(nullable=false)
	private String street;
	
	@Column(nullable=false)
	private int number;
	
	@Column(name ="zip_code")
	private String zipCode;

	@Column(nullable=false)
	private String city;
	
	@Column(nullable=false)
	private String country;
	

	@Column
	private Float langtitude;
	
	@Column 
	private Float latitude;	
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_datetime")
	private Date createdDateTime;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_datetime")
	private Date updatedDateTime;
	
	public Float getLangtitude() {
		return langtitude;
	}

	public void setLangtitude(Float langtitude) {
		this.langtitude = langtitude;
	}

	
	public Address() {
		super();
		
	}
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getAddressId() {
		return addressId;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void addEmployee(Employee employee) {
		this.employees.add(employee);
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}



	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", street=" + street + ", number=" + number + ", zipCode=" + zipCode
				+ ", langitude=" + langtitude + ", latitude=" + latitude + ", createdDateTime=" + createdDateTime
				+ ", updatedDateTime=" + updatedDateTime + "]";
	}
	
	
	
	
}