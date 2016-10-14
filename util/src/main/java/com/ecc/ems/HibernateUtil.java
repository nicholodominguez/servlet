package com.ecc.ems;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil{
    
    public static final SessionFactory factory;
    public static final Transaction currentTransaction;
    public static final Session currentsession;
    
    static{
        try {
            Configuration config = new Configuration().configure();
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
            factory = config.buildSessionFactory(registry); //CONTINUE HERE
        }
        catch(HibernateException e){
            System.out.println("Initial creation failed. " + e);
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public static Session getCurrentSession(){
        currentSession = factory.openSession();
        
        currentTransaction = session.beginTransaction();
        return currentSession;
    }

    public static void commit(){
        this.currentTransaction.commit();
    }
}
