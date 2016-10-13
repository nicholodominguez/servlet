package com.ecc.ems;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table (name = "contact")
public class Contact extends BaseEntity{
    private String contactType;
    private String contactDetails;
    private int empId;
    
    public Contact() {}
    
    public Contact(String contactType, String contactDetails, int empId, boolean status) {
        super(status);
        this.contactType = contactType;
        this.empId = empId;
        this.contactDetails = contactDetails;    
    }
    
    @Column (name = "contact_type")
    public String getContactType() {
        return contactType;
    }
    
    @Column (name = "contact_details")
    public String getContactDetails() {
        return contactDetails;
    }
    
    @Column (name = "emp_id")
    public int getEmpId() {
        return empId;
    }
    
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }
    
    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
    
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    
    public String stringify(){
        return this.contactType + ": " + this.contactDetails; 
    }
}
