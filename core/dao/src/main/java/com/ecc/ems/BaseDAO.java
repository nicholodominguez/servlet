package com.ecc.ems;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

import com.ecc.ems.BaseDAOInterface;

public abstract class BaseDAO<T, Id extends Serializable> implements BaseDAOInterface<T, Id>{
    
    protected SessionFactory factory;
    protected Session currentSession;
    protected Transaction currentTransaction;
    protected Class<T> clazz;
    
    public BaseDAO(SessionFactory factory, Class<T> clazz) {
        this.factory = factory;    
        this.clazz = clazz;
    }
    
    public void saveOrUpdate(T entity) {
        this.currentSession = factory.openSession();
        
        try{
            this.currentTransaction = this.currentSession.beginTransaction();
            this.currentSession.saveOrUpdate(entity);
            this.currentSession.flush();
            this.currentTransaction.commit();
        }catch (HibernateException e) {
            if (this.currentTransaction != null) {
                this.currentTransaction.rollback();
            }
        }finally {
            this.currentSession.close();
        }
    }
    
    public void update(T entity) {
        this.currentSession = factory.openSession();
        
        try{
            this.currentTransaction = this.currentSession.beginTransaction();
            this.currentSession.update(entity);
            this.currentSession.flush();
            this.currentTransaction.commit();
        }catch (HibernateException e) {
            if (this.currentTransaction != null) {
                this.currentTransaction.rollback();
            }
        }finally {
            this.currentSession.close();
        }
    }
    
    public T findById(Id id) {
        T entity = null;
        this.currentSession = factory.openSession();
        
        try{
            this.currentTransaction = this.currentSession.beginTransaction();
            entity = (T) this.currentSession.get(this.clazz.getName(), id);
            this.currentTransaction.commit();
        }catch (HibernateException e) {
            if (this.currentTransaction != null) {
                this.currentTransaction.rollback();
            }
        }finally {
            this.currentSession.close();
        }
        
        return entity;
    }
    
    public List<T> findAll(String query) {
        List<T> entities = null;
        this.currentSession = this.factory.openSession();
	    
	    try{
            this.currentTransaction = this.currentSession.beginTransaction();
            entities = (List<T>) this.currentSession.createQuery(query).list();
            this.currentTransaction.commit();
        }catch (HibernateException e) {
            if (this.currentTransaction != null) {
                this.currentTransaction.rollback();
            }
            System.out.println(e);
        }finally {
            this.currentSession.close();
        }    
	    
	    return entities;
    }
    
    public void delete(T entity){
        this.currentSession = factory.openSession();
        
        try{
            this.currentTransaction = this.currentSession.beginTransaction();
            this.currentSession.delete(entity);
            this.currentSession.flush();
            this.currentTransaction.commit();
        }catch (HibernateException e) {
            if (this.currentTransaction != null) {
                this.currentTransaction.rollback();
            }
        }finally {
            this.currentSession.close();
        }   
    }
}
