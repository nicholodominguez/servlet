package com.ecc.ems;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria; 
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Projections;

import com.ecc.ems.Employee;
import com.ecc.ems.EmployeeDAOInterface;
import com.ecc.ems.BaseDAO;
import java.util.List;
import java.util.Collections;

public class EmployeeDAO extends BaseDAO<Employee, Integer> implements EmployeeDAOInterface{
    
    private Criteria crit;
    
    public EmployeeDAO(SessionFactory factory) {
        super(factory, Employee.class);
    }
    
    public List<Employee> sortByLastname(boolean ifAscending){
        
        List<Employee> entities = null;
        this.currentSession = factory.openSession();
	    this.crit = this.currentSession.createCriteria(Employee.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	    
	    if(ifAscending){
	        this.crit.addOrder( Order.asc("name.lastname") );    
	    }
	    else{
	        this.crit.addOrder( Order.desc("name.lastname") );
	    }
	    
	    try{
            this.currentTransaction = this.currentSession.beginTransaction();
            entities = this.crit.list();
            this.currentTransaction.commit();
        }catch (HibernateException e) {
            if (this.currentTransaction != null) {
                this.currentTransaction.rollback();
            }
        }finally {
            this.currentSession.close();
        }    
	    
	    return entities;
    }
    
    public List<Employee> sortByDateHired(boolean ifAscending){
        
        List<Employee> entities = null;
        this.currentSession = factory.openSession();
	    this.crit = this.currentSession.createCriteria(Employee.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	    
	    if(ifAscending){
	        this.crit.addOrder( Order.asc("dateHired") );    
	    }
	    else{
	        this.crit.addOrder( Order.desc("dateHired") );
	    }
	    
	    try{
            this.currentTransaction = this.currentSession.beginTransaction();
            entities = this.crit.list();
            this.currentTransaction.commit();
        }catch (HibernateException e) {
            if (this.currentTransaction != null) {
                this.currentTransaction.rollback();
            }
        }finally {
            this.currentSession.close();
        }    
	    
	    return entities;
    
    }
    
    public List<Employee> sortByGwa(boolean ifAscending){
    
        List<Employee> entities = null;
        this.currentSession = factory.openSession();
	    
	    try{
            this.currentTransaction = this.currentSession.beginTransaction();
            entities = (List<Employee>) this.currentSession.createQuery("from Employee").list();
            if (ifAscending){
                Collections.sort(entities, Collections.reverseOrder());
            }
            else{
                Collections.sort(entities);
            }
            this.currentTransaction.commit();
        }catch (HibernateException e) {
            if (this.currentTransaction != null) {
                this.currentTransaction.rollback();
            }
        }finally {
            this.currentSession.close();
        }    
	    
	    return entities;
    }
    
    public List<Employee> searchEmployee(String keyword){
    
        List<Employee> entities = null;
        this.currentSession = factory.openSession();
	    this.crit = this.currentSession.createCriteria(Employee.class).add(Restrictions.disjunction()
	                  .add(Restrictions.sqlRestriction("firstname like '%"+keyword+"%'"))
	                  .add(Restrictions.sqlRestriction("middlename like '%"+keyword+"%'"))
	                  .add(Restrictions.sqlRestriction("lastname like '%"+keyword+"%'"))
	                  .add(Restrictions.sqlRestriction("suffix like '%"+keyword+"%'")))
	                  .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	    
	    try{
            this.currentTransaction = this.currentSession.beginTransaction();
            entities = this.crit.list();
            this.currentTransaction.commit();
        }catch (HibernateException e) {
            if (this.currentTransaction != null) {
                this.currentTransaction.rollback();
            }
        }finally {
            this.currentSession.close();
        }    
	    
	    return entities;
    }
}
