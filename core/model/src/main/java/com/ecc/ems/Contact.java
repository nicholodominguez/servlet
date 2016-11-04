package com.ecc.ems;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Cacheable;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.AttributeOverride;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.ecc.ems.Employee;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "Contact")
@Table (name = "contact")
@AttributeOverride(name="id", column = @Column(name = "contact_id"))
public class Contact extends BaseEntity{
    private String contactType;
    private String contactDetails;
    private Employee emp;
    
    public Contact() {}
    
    public Contact(String contactType, String contactDetails, Employee emp, boolean status) {
        super(status);
        this.contactType = contactType;
        this.contactDetails = contactDetails;
        this.emp = emp;    
    }
    
    @Column (name = "contact_type")
    public String getContactType() {
        return contactType;
    }
    
    @Column (name = "contact_details")
    public String getContactDetails() {
        return contactDetails;
    }
    
    @ManyToOne
    @JoinColumn(
        name = "emp_id",
        nullable = false
    )
    public Employee getEmp(){
        return emp;
    }
    
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }
    
    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
    
    public void setEmp(Employee emp){
        this.emp = emp;
    }
    
    public String stringify(){
        return this.contactType + ": " + this.contactDetails; 
    }
}
