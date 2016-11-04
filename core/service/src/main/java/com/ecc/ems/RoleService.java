package com.ecc.ems;

import java.util.List;
import java.util.Set;

import com.ecc.ems.RoleDAO;
import com.ecc.ems.Role;

public class RoleService{
    
    private RoleDAO roleDao;
    
    public RoleService() {  
        roleDao = new RoleDAO();
    }    
    
    public List<Role> getAvailableRoles(Set<Role> roles){
        List<Role> roleList = null;
        
        if(roles.size() > 0){
            roleList = roleDao.getAssignableRoles(roles);
        }
        else{
            roleList = roleDao.findAll("from Role");
        }
        return roleList;
    }
    
    public void addRole(Role role){
        roleDao.saveOrUpdate(role);
    }
    
    public List<Role> listRoles(){
        List<Role> roleList = null;
        roleList = roleDao.findAll("from Role");
        
        return roleList;
    }
    
    public void updateRole(Role role){
        roleDao.update(role);
    }
    
    public void deleteRole(Role role){
        roleDao.delete(role);
    }
    
    public Role findById(int id){
        return roleDao.findById(id);
    }   
}
