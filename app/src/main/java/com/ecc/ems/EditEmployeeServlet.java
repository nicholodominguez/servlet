package com.ecc.ems;

import java.io.IOException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;   

import com.ecc.ems.HibernateUtil;
import com.ecc.ems.EmployeeService;
import com.ecc.ems.RoleService;
import com.ecc.ems.Employee;
import com.ecc.ems.Name;
import com.ecc.ems.Address;
import com.ecc.ems.Contact;
import com.ecc.ems.Role;

public class EditEmployeeServlet extends HttpServlet {
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        EmployeeService es = new EmployeeService();
        RoleService rs = new RoleService();
        Employee emp;
        List<Role> availableRoles;

        if(request.getParameter("id").compareTo("") == 0){
            emp = null;
            availableRoles = (List<Role>) rs.listRoles();
            request.setAttribute("header", "Add Employee");
        }
        else{
            int empId = Integer.parseInt(request.getParameter("id"));
            emp = (Employee) es.searchEmployeeById(empId);
            
            request.setAttribute("header", "Edit Employee");
            availableRoles = (List<Role>) rs.getAvailableRoles(emp.getRoles());
        }
        
        response.setStatus(HttpServletResponse.SC_OK);
        request.setAttribute("employee", emp);
        request.setAttribute("roles", availableRoles);
        request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeService es = new EmployeeService();
        RoleService rs = new RoleService();
        Employee editedEmp = null;
        Integer empId;
        Set editedContacts;
        Set editedRoles = new HashSet<Role>();
        Name editedName = new Name();
        Address editedAddress = new Address();
        
        if(request.getParameter("empId").compareTo("") == 0){
            editedEmp = new Employee();
            empId = null;
            editedContacts = new HashSet<Contact>();
        }
        else{
            empId = Integer.parseInt(request.getParameter("empId"));
            editedEmp = es.searchEmployeeById(empId);
            editedContacts = editedEmp.getContacts();
            editedContacts.clear();
        }
        
        
        SimpleDateFormat dateInput = new SimpleDateFormat("MMMMM dd, yyyy", Locale.ENGLISH);
        SimpleDateFormat dateOutput = new SimpleDateFormat("yyyy-MM-dd");
        
        
        editedName.setTitle(request.getParameter("title"));
        editedName.setFirstname(request.getParameter("firstname"));
        editedName.setMiddlename(request.getParameter("middlename"));
        editedName.setLastname(request.getParameter("lastname"));
        editedName.setSuffix(request.getParameter("suffix"));
        editedEmp.setName(editedName);
        
        editedAddress.setStreet(request.getParameter("street"));
        editedAddress.setBrgy(request.getParameter("brgy"));
        editedAddress.setMunicipality(request.getParameter("municipality"));
        editedAddress.setCountry(request.getParameter("country"));
        editedAddress.setZipcode(request.getParameter("zipcode"));
        editedEmp.setAddress(editedAddress);
        
        try{
            editedEmp.setBdate(dateOutput.parse(dateOutput.format(dateInput.parse(request.getParameter("birthdate")))));
            editedEmp.setDateHired(dateOutput.parse(dateOutput.format(dateInput.parse(request.getParameter("dateHired")))));
        }
        catch(ParseException e){}
        
        editedEmp.setGwa(Double.parseDouble(request.getParameter("gwa")));
        
        String[] ids = request.getParameterValues("contact_id");
        String[] types = request.getParameterValues("contact_type");
        String[] details = request.getParameterValues("contact_details");
        
        for(int i = 0; i < details.length; i++){
            Contact contact = null;
            
            if(ids[i].compareTo("new") == 0){
                contact = new Contact(types[i], details[i], editedEmp, true);
            }
            else{
                contact = (Contact) es.findContactById(Integer.parseInt(ids[i]));
                contact.setContactType(types[i]);
                contact.setContactDetails(details[i]);
            }
            
            editedContacts.add(contact);
        }
        
        editedEmp.setContacts(editedContacts);
        
        String[] roles = request.getParameterValues("roles");
        
        if(roles != null){
            for(String rl : roles){        
                Integer roleId = Integer.parseInt(rl);
                Role role = rs.findById(roleId);
                editedRoles.add(role);
            }
        }
        
        editedEmp.setRoles(editedRoles);
        
        es.addEmployee(editedEmp);
        
        response.sendRedirect(request.getContextPath());
    }
}
