package com.ecc.ems;

import com.ecc.ems.Employee;
import java.util.List;

import com.ecc.ems.BaseDAOInterface;

public interface EmployeeDAOInterface extends BaseDAOInterface<Employee, Integer>{
    
    public List<Employee> sortByLastname(boolean ifAscending);
    
    public List<Employee> sortByDateHired(boolean ifAscending);
    
    public List<Employee> sortByGwa(boolean ifAscending);
    
    public List<Employee> searchEmployee(String keyword);

}
