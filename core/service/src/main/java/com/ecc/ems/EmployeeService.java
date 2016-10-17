package com.ecc.ems;

import java.util.List;
import java.util.Set;

import com.ecc.ems.HibernateUtil;
import com.ecc.ems.EmployeeDAO;
import com.ecc.ems.RoleDAO;
import com.ecc.ems.Employee;
import com.ecc.ems.Role;

public class EmployeeService{
    
    private EmployeeDAO emDao;
    private RoleDAO roleDao;
    
    public EmployeeService() {  
        emDao = new EmployeeDAO();
        roleDao = new RoleDAO();
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
    
    public List<Role> getAvailableRoles(Set<Role> roles){
        List<Role> roleList = null;
        
        if(roles.size() > 0){
            roleList = roleDao.getAssignableRoles(roles);
        }
        else{
            roleList = roleDao.findAll("from Role");
        }
        return roleList;
    }
    
    public void addRole(Role role){
        roleDao.saveOrUpdate(role);
    }
    
    public List<Role> listRoles(){
        List<Role> roleList = null;
        roleList = roleDao.findAll("from Role");
        
        return roleList;
    }
    
    public void updateRole(Role role){
        roleDao.update(role);
    }
    
    public void deleteRole(Role role){
        roleDao.delete(role);
    }
    
    public void terminate(){
        HibernateUtil.closeSessionFactory();
    }

}
