package com.ecc.ems;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria; 
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import com.ecc.ems.Role;
import java.util.List;
import java.util.Set;
import java.util.Collection;

public class RoleDAO extends BaseDAO<Role, Integer> implements RoleDAOInterface{
        
    public RoleDAO(SessionFactory factory){
        super(factory, Role.class);
    }
        
    public List<Role> getAssignableRoles(Set<Role> roles){
        List<Role> entities = null;
        this.currentSession = factory.openSession();
        
	    Criteria crit = this.currentSession.createCriteria(Role.class);
	    Disjunction disj = Restrictions.disjunction();
	    
	    for(Role role : roles){
	        disj.add(Restrictions.sqlRestriction("role_name != '" + role.getName() + "'"));
	    }
	    
	    crit.add(disj);
	    
	    try{
            this.currentTransaction = this.currentSession.beginTransaction();
            entities = crit.list();
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
