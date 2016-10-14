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

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.AttributeOverride;

@Entity
@Table(name="employee")
@AttributeOverride(name = "id", column = @Column(name = "emp_id"))
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
    
    @Embedded
    public Address getAddress() {
        return address;
    }
    
    @Column (name = "bdate")
    public Date getBdate() {
        return bdate;
    }
    
    @Column (name = "gwa")
    public Double getGwa() {
        return gwa;
    }
    
    @OneToMany(
        cascade = CascadeType.ALL, 
        fetch = FetchType.EAGER,
        targetEntity = Contact.class 
    )
    @JoinColumn(
        name = "emp_id"
    )
    public Set getContacts() {
        return contacts;
    }
    
    @Column (name = "date_hired")
    public Date getDateHired() {
        return dateHired;
    }
    
    @ManyToMany(
        cascade = {CascadeType.PERSIST, CascadeType.MERGE}, 
        fetch = FetchType.EAGER,
        targetEntity = Role.class
    )
    @JoinTable(
        name = "emp_role",
        joinColumns = { @JoinColumn(name = "emp_id", nullable = false, updatable = false) }, 
        inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) }
    )
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
	        list.add("Name: " + this.name.fullname());
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
