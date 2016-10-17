package com.ecc.ems;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria; 
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import com.ecc.ems.Role;
import com.ecc.ems.RoleDAOInterface;
import com.ecc.ems.BaseDAO;
import com.ecc.ems.HibernateUtil;

import java.util.List;
import java.util.Set;
import java.util.Collection;

public class RoleDAO extends BaseDAO<Role, Integer> implements RoleDAOInterface{
        
    public RoleDAO(){
        super(Role.class);
    }
        
    public List<Role> getAssignableRoles(Set<Role> roles){
        List<Role> entities = null;
        
	    Criteria crit = HibernateUtil.createAndGetCurrentSession().createCriteria(Role.class);
	    Disjunction disj = Restrictions.disjunction();
	    
	    for(Role role : roles){
	        disj.add(Restrictions.ne("role_name", role.getName()));
	    }
	    
	    crit.add(disj);
	    
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
}
