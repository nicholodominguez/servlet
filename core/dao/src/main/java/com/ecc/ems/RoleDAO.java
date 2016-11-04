package com.ecc.ems;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria; 
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.MatchMode;

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
	    
	    for(Role role : roles){
	        crit.add(Restrictions.ne("name", role.getName()));
	    }
	    
	    try{
            entities = crit.list();
            System.out.println(entities.size());
            HibernateUtil.commit();
        }catch (HibernateException e) {
            e.printStackTrace();
            HibernateUtil.rollback();
        }finally {
            HibernateUtil.closeCurrentSession();
        }    
	    
	    return entities;
    }
}
