package com.ecc.ems;

import java.util.List;
import java.util.ArrayList;

public class Address{
    private String street;
    private String brgy;
    private String municipality;
    private String zipcode;
    private String country;
    
    public Address() {}
    
    public Address(String street, String brgy, String municipality, String zipcode, String country) {    
        this.street = street;
        this.brgy = brgy;
        this.municipality = municipality;
        this.zipcode = zipcode;
        this.country = country;
    }
    
    public String getStreet() {
        return street;
    }
    
    public String getBrgy() {
        return brgy;
    }
    
    public String getMunicipality() {
        return municipality;
    }
    
    public String getZipcode() {
        return zipcode;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    public void setBrgy(String brgy) {
        this.brgy = brgy;
    }
    
    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }
    
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String toString() {
        String result = "";
        
        if(this.getStreet() != null){
	        result = result.concat(this.getStreet());
	    }
	    
	    if(this.getBrgy() != null){
	        result = result.concat(", ").concat(this.getBrgy());
	    }
	    
	    if(this.getMunicipality() != null){
	        result = result.concat(", ").concat(this.getMunicipality());
	    }
	    
	    if(this.getCountry() != null){
	        result = result.concat(", ").concat(this.getCountry());
	    }
	    
	    if(this.getZipcode() != null){
	        result = result.concat(", ").concat(this.getZipcode());
	    }
	    
	    return result;
    }
    
    public List<String> stringify(){
        ArrayList<String> list = new ArrayList();
	    
	    if(this.getStreet() != null){
	        list.add("Street: " + this.getStreet());
	    }
	    else{
	        list.add("Street: ");
	    }
	    
	    if(this.getBrgy() != null){
	        list.add("Brgy: " + this.getBrgy());
	    }
	    else{
	        list.add("Brgy: ");
	    }
	    
	    if(this.getMunicipality() != null){
	        list.add("Municipality: " + this.getMunicipality());
	    }
	    else{
	        list.add("Municipality: ");
	    }
	    
	    if(this.getCountry() != null){
	        list.add("Country: " + this.getCountry());
	    }
	    else{
	        list.add("Country: ");
	    }
	    
	    if(this.getZipcode() != null){
	        list.add("Zipcode: " + this.getZipcode());	
        }
        else{
            list.add("Zipcode: ");
        }
        
        return list;
    }
}
