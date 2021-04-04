package com.bill.employee.routing.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;

import javax.persistence.ForeignKey;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="employee_id")
	private Long employeeId;
	
	 @ManyToMany
	 @JoinTable(
	   name = "employee_attribute",
	   joinColumns = { @JoinColumn(name = "employee_id") },
	   inverseJoinColumns = { @JoinColumn(name = "attribute_id") }
	)
	private Set<Attribute> attributes = new HashSet<>();

	@Column(name ="address_id")
	private Long addressId;
	
	@Column(name ="first_name",nullable = false)
	private String firstName;
	
	@Column(name ="last_name",nullable = false)
	private String lastName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id",referencedColumnName="address_id", insertable=false, updatable=false)
	private Address address;
	
	@Column(name ="date_of_birthday",nullable = false)
	private Date dateOfBirthday;	
	
	@Column(name="has_vehicle",columnDefinition="tinyint(1) default 1")
	private boolean hasVehicle;	

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_datetime")
	private Date createdDateTime;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_datetime")
	private Date updatedDateTime;

	
	public Employee() {
		
	}
	
	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Attribute> getAttributes() {
		return attributes;
	}

	public boolean isAttributeExistById(Attribute attribute) {
		
		return this.attributes.contains(attribute);
	} 
	
	public Long getEmployeeId() {
		return employeeId;
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

	public Date getDateOfBirthday() {
		return dateOfBirthday;
	}


	public void setDateOfBirthday(Date dateOfBirthday) {
		this.dateOfBirthday = dateOfBirthday;
	}


	public boolean isHasVehicle() {
		return hasVehicle;
	}


	public void setHasVehicle(boolean hasVehicle) {
		this.hasVehicle = hasVehicle;
	}

	//add atribute to list of meployee's attributes 
	public void addAttibute(Attribute attribute) {
		
		if(attribute.getEmployees().size()>0) 
		{
			attribute.getEmployees().add(this);			
			this.attributes.add(attribute);
		}
	}
	//remove atribute from list of meployee's attributes 
	public void removeAttibute(Attribute attribute) {
		
		attribute.removeEmployee(this);
		if(this.attributes.contains(attribute))
			this.attributes.remove(attribute);			

	}
	
	//update list of meployee's attributes 
	public void updateAttributeList(Set<Attribute> updateAttributeList) {
			
		this.attributes.stream()	
			.forEach( attribute->attribute.removeEmployee(this) );		
		
		this.attributes.clear();
		this.attributes =updateAttributeList;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", attributes=" + attributes + ", addressId=" + addressId
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address.toString() + ", dateOfBirthday="
				+ dateOfBirthday + ", hasVehicle=" + hasVehicle + ", createdDateTime=" + createdDateTime
				+ ", updatedDateTime=" + updatedDateTime + "]";
	}


	
	
}

