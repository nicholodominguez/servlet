package com.ecc.ems;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

import com.ecc.ems.BaseDAOInterface;
import com.ecc.ems.HibernateUtil;

public abstract class BaseDAO<T, Id extends Serializable> implements BaseDAOInterface<T, Id>{
    
    protected Class<T> clazz;
    
    public BaseDAO(Class<T> clazz) {    
        this.clazz = clazz;
    }
    
    public void saveOrUpdate(T entity) {
        try{
            HibernateUtil.createAndGetCurrentSession().saveOrUpdate(entity);
            HibernateUtil.commit();
        }catch (HibernateException e) {
            e.printStackTrace();
            HibernateUtil.rollback(); 
        }finally {
            HibernateUtil.closeCurrentSession();
        }
    }
    
    public void update(T entity) {
        
        try{
            HibernateUtil.createAndGetCurrentSession().update(entity);
            
            System.out.println("EMDAO HERE");
            HibernateUtil.commit();
        }catch (HibernateException e) {
            e.printStackTrace();
            HibernateUtil.rollback(); 
        }finally {
            HibernateUtil.closeCurrentSession();
        }
    }
    
    public T findById(Id id) {
        T entity = null;
        
        try{
            entity = clazz.cast(HibernateUtil.createAndGetCurrentSession().get(clazz.getName(), id));
            HibernateUtil.commit();
        }catch (HibernateException e) {
            e.printStackTrace();
            HibernateUtil.rollback();
        }finally {
            HibernateUtil.closeCurrentSession();
        }
        
        return entity;
    }
    
    public List<T> findAll(String query) {
        List<T> entities = null;
	    
        try{
            entities = (List<T>) HibernateUtil.createAndGetCurrentSession().createQuery(query).list();
            HibernateUtil.commit();
        }catch (HibernateException e) {
            e.printStackTrace();
            HibernateUtil.rollback();
        }finally {
            HibernateUtil.closeCurrentSession();
        }
	    
	    return entities;
    }
    
    public void delete(T entity){
                
        try{
            HibernateUtil.createAndGetCurrentSession().delete(entity);
            HibernateUtil.commit();
        }catch (HibernateException e) {
            e.printStackTrace();
            HibernateUtil.rollback(); 
        }finally {
            HibernateUtil.closeCurrentSession();
        }
    }
}
