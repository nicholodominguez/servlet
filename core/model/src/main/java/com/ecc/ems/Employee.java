package com.ecc.ems;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.text.Format;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.lang.Comparable;

import com.ecc.ems.Name;
import com.ecc.ems.Address;
import com.ecc.ems.Contact;
import com.ecc.ems.Role;

@Entity
@Table(name="employee")
public class Employee extends BaseEntity implements Comparable<Employee>{
    private Name name;
    private Address address;
    private Date bdate;
    private Double gwa;
    private Set<Contact> contacts;
    private Date dateHired;
    private Set<Role> roles;
    
    public Employee() {}
    
    public Employee(Name name, Address address, Date bdate, Double gwa, Set contacts, Date dateHired, Set roles, boolean status) {
        super(status);
        this.name = name;
        this.address = address;
        this.bdate = bdate;
        this.gwa = gwa;
        this.contacts = contacts;
        this.dateHired = dateHired;    
        this.roles = roles;    
    }
    
    @Embedded
    public Name getName() {
        return name;
    }
    
    public Address getAddress() {
        return address;
    }
    
    public Date getBdate() {
        return bdate;
    }
    
    public Double getGwa() {
        return gwa;
    }
    
    public Set getContacts() {
        return contacts;
    }
    
    public Date getDateHired() {
        return dateHired;
    }
    
    public Set getRoles() {
        return roles;
    }
    
    public void setName(Name name) {
        this.name = name;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }
    
    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }
    
    public void setGwa(Double gwa) {
        this.gwa = gwa;
    }
    
    public void setContacts(Set contacts) {
        this.contacts = contacts;
    }
    
    public void setDateHired(Date dateHired) {
        this.dateHired = dateHired;
    }
    
    public void setRoles(Set roles) {
        this.roles = roles;
    }
    
    public int compareTo(Employee compareEmp) {
		double compareGwa = ((Employee) compareEmp).getGwa();
		return (int)(this.getGwa() - compareGwa);
	}
	
	public List<String> stringify() {
	    DateFormat df = new SimpleDateFormat("MMM/dd/yyyy");
	    
	    ArrayList<String> list = new ArrayList();
	    
	    if(this.name != null){
	        list.add("Name: " + this.name.getFullname());
	    }
	    else{
	        list.add("Name: ");
	    }
	    
	    if(this.address != null){
	        list.add("Address: " + this.address.toString());
	    }
	    else{
	        list.add("Address: ");
	    }
	    
	    if(this.getBdate() != null){
	        list.add("Birthdate: " + df.format(this.getBdate()));
        }
	    else{
	        list.add("Birthdate: ");
	    }
	    
	    if(this.getGwa() != null){
	        list.add("GWA: " + String.valueOf(this.getGwa()));	
        }
	    else{
	        list.add("GWA: ");
	    }
	    
	    if(this.getDateHired() != null){
	        list.add("Date Hired: " + df.format(this.getDateHired()));	
        }
	    else{
	        list.add("Date Hired: ");
	    }
	    
        return list;
	}
}
