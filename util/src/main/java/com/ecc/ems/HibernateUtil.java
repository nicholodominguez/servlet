package com.ecc.ems;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Map;

public class HibernateUtil{
    
    private static final SessionFactory factory;
    private static Transaction currentTransaction;
    private static Session currentSession;
    
    static{
        try {
            Configuration config = new Configuration().configure();
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
            factory = config.buildSessionFactory(registry);
        }
        catch(HibernateException e){
            System.out.println("Initial creation failed. " + e);
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public static void createNewSession(){
        currentSession = factory.openSession();
    }
    
    public static Session getCurrentSession(){
        return currentSession;
    }
    
    public static void createNewTransaction(){
        currentTransaction = currentSession.beginTransaction();
    }
    
    public static Session createAndGetCurrentSession(){
        createNewSession();
        
        currentTransaction = currentSession.beginTransaction();
        return getCurrentSession();
    }
    
    public static void closeCurrentSession(){
        if(currentSession.isOpen()){
            currentSession.close();
        }
    }
    
    public static void commit(){
        currentTransaction.commit();
    }

    public static void rollback(){
        if(currentTransaction != null){
            currentTransaction.rollback();
        }
    }
    
    public static void closeSessionFactory(){
        factory.close();
    }
}
