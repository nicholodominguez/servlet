package com.ecc.ems;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import com.ecc.ems.Contact;

public interface ContactDAOInterface extends BaseDAOInterface<Contact, Integer>{
    
}
