package com.ecc.ems;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import com.ecc.ems.Contact;
import com.ecc.ems.ContactDAOInterface;
import com.ecc.ems.BaseDAO;

public class ContactDAO extends BaseDAO<Contact, Integer> implements ContactDAOInterface {
    
    public ContactDAO() {
        super(Contact.class);
    }
}
