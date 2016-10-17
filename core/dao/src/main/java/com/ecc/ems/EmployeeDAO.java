package com.ecc.ems;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria; 
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.MatchMode;

import com.ecc.ems.Employee;
import com.ecc.ems.EmployeeDAOInterface;
import com.ecc.ems.BaseDAO;
import com.ecc.ems.HibernateUtil;

import java.util.List;
import java.util.Collections;

public class EmployeeDAO extends BaseDAO<Employee, Integer> implements EmployeeDAOInterface{
        
    public EmployeeDAO() {
        super(Employee.class);
        HibernateUtil.createNewSession();
    }
    
    public List<Employee> sortByLastname(boolean ifAscending){
        
        List<Employee> entities = null;
        Criteria crit = HibernateUtil.createAndGetCurrentSession().createCriteria(Employee.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        
	    if(ifAscending){
	        crit.addOrder( Order.asc("name.lastname") );    
	    }
	    else{
	        crit.addOrder( Order.desc("name.lastname") );
	    }
        
        try{
            entities = crit.list();
        }catch (HibernateException e) {
            HibernateUtil.rollback(); 
        }finally {
            HibernateUtil.closeCurrentSession();
        }
        
	    return entities;
    }
    
    public List<Employee> sortByDateHired(boolean ifAscending){
        
        List<Employee> entities = null;
        Criteria crit = HibernateUtil.createAndGetCurrentSession().createCriteria(Employee.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        
	    if(ifAscending){
	        crit.addOrder( Order.asc("dateHired") );    
	    }
	    else{
	        crit.addOrder( Order.desc("dateHired") );
	    }
	    
	    try{
            entities = crit.list();
            HibernateUtil.commit();
        }catch (HibernateException e) {
            HibernateUtil.rollback(); 
        }finally {
            HibernateUtil.closeCurrentSession();
        }    
	    
	    return entities;
    
    }
    
    public List<Employee> sortByGwa(boolean ifAscending){
    
        List<Employee> entities = null;
	    
	    try{
            entities = (List<Employee>) HibernateUtil.createAndGetCurrentSession().createQuery("from Employee").list();
            if (ifAscending){
                Collections.sort(entities, Collections.reverseOrder());
            }
            else{
                Collections.sort(entities);
            }
            HibernateUtil.commit();
        }catch (HibernateException e) {
            HibernateUtil.rollback(); 
        }finally {
            HibernateUtil.closeCurrentSession();
        }    
	    
	    return entities;
    }
    
    public List<Employee> searchEmployee(String keyword){
    
        List<Employee> entities = null;
        Criteria crit = HibernateUtil.createAndGetCurrentSession().createCriteria(Employee.class).add(Restrictions.disjunction()
	                  .add(Restrictions.sqlRestriction("firstname like '%"+keyword+"%'"))
	                  .add(Restrictions.sqlRestriction("middlename like '%"+keyword+"%'"))
	                  .add(Restrictions.sqlRestriction("lastname like '%"+keyword+"%'"))
	                  .add(Restrictions.sqlRestriction("suffix like '%"+keyword+"%'")))
	                  .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	    
	    try{
            entities = (List<Employee>) crit.list();
            System.out.println(entities);
            HibernateUtil.commit();
        }catch (HibernateException e) {
	        HibernateUtil.rollback(); 
        }finally {
	        HibernateUtil.closeCurrentSession();
        }    
	    
	    return entities;
    }
}
