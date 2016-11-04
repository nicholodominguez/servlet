package com.ecc.ems;

import java.util.List;
import java.util.Set;

import com.ecc.ems.EmployeeDAO;
import com.ecc.ems.Employee;
import com.ecc.ems.ContactDAO;
import com.ecc.ems.Contact;

public class EmployeeService{
    
    private EmployeeDAO emDao;
    private ContactDAO conDao;
    
    public EmployeeService() {  
        emDao = new EmployeeDAO();
        conDao = new ContactDAO();
    }
    
    public void addEmployee(Employee emp){
        emDao.saveOrUpdate(emp);
    }
    
    public void updateEmployee(Employee emp){
        emDao.update(emp);
    }
    
    public List<Employee> listEmployee(){
        List<Employee> empList = null;
        empList = emDao.findAll("from Employee");
        return empList;
    }
    
    public List listEmployeeByLastname(boolean ascending){
        List<Employee> empList = null;
        
        empList = emDao.sortByLastname(ascending);
        return empList;
    }
    
    public List listEmployeeByDateHired(boolean ascending){
        List<Employee> empList = null;
        
        empList = emDao.sortByDateHired(ascending);
        return empList;
    }
    
    public List listEmployeeByGwa(boolean ascending){
        List<Employee> empList = null;
        
        empList = emDao.sortByGwa(ascending);
        return empList;
    }
    
    public void deleteEmployee(Employee emp){
        emDao.delete(emp);
    }
    
    public List searchEmployeeByName(String keyword){
        List<Employee> empList = null;
        
        empList = emDao.searchEmployee(keyword);
        return empList;
    }
    
    public Employee searchEmployeeById(int id){
        Employee employee = null;
        
        employee = emDao.findById(id);
        return employee;
    }
    
    public Contact findContactById(int id) {
        Contact contact = null;
        
        contact = conDao.findById(id);
        return contact;
    }
    
    public void saveOrUpdateContact(Contact contact){
        conDao.saveOrUpdate(contact);
    }

}
