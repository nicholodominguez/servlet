package com.ecc.ems;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.ecc.ems.EmployeeDAO;
import com.ecc.ems.RoleDAO;
import com.ecc.ems.Employee;
import com.ecc.ems.Role;

public class EmployeeService{
    
    private SessionFactory factory;
    private EmployeeDAO emDao;
    private RoleDAO roleDao;
    private String FILEPATH = "hibernate.cfg.xml";
    private File configFile = new File(FILEPATH);
    
    
    public EmployeeService() {
    
        try {
            Configuration config = new Configuration().configure(FILEPATH);
            //Configuration config = new Configuration().configure("/infra/src/main/resources/persistence/ems.cfg.xml");
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
            factory = config.buildSessionFactory(registry);
            this.emDao = new EmployeeDAO(factory);
            this.roleDao = new RoleDAO(factory);
        }
        catch(HibernateException e){
            System.out.println("Initial creation failed. " + e);
            throw new ExceptionInInitializerError(e);
        }
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

}
