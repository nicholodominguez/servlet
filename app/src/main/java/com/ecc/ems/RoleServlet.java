package com.ecc.ems;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;  

import com.ecc.ems.HibernateUtil;
import com.ecc.ems.RoleService;
import com.ecc.ems.Role;

public class RoleServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        RoleService rs = new RoleService();
        List<Role> roles = rs.listRoles();
    
        response.setStatus(HttpServletResponse.SC_OK);
        request.setAttribute("header", "Role List");
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("/WEB-INF/jsp/role.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        RoleService rs = new RoleService();
        
        if(request.getParameter("newRole") != null){
            Role newRole;
            if(request.getParameter("newRoleId") != null){
                newRole = rs.findById(Integer.parseInt(request.getParameter("newRoleId")));
                newRole.setName(request.getParameter("newRole"));
            }
            else{
                newRole = new Role(request.getParameter("newRole"), true);
            }
            rs.addRole(newRole);
        }
        else if(request.getParameter("roleid_delete") != null){
            Role role = rs.findById(Integer.parseInt(request.getParameter("roleid")));
            if(role.getEmployees().size() == 0){
                rs.deleteRole(role);
            }
            else{
                System.out.println(role.getName() + " role deletion unsuccessful.");   
            }    
        }
        
        doGet(request, response);
    }

}
