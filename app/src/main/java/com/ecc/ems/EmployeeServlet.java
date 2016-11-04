package com.ecc.ems;

import java.io.IOException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Date;
import java.util.HashSet;
import java.text.SimpleDateFormat;  

import com.ecc.ems.HibernateUtil;
import com.ecc.ems.EmployeeService;
import com.ecc.ems.Employee;
import com.ecc.ems.RoleService;
import com.ecc.ems.Role;

public class EmployeeServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        EmployeeService es = new EmployeeService();
        List<Employee> employees = es.listEmployee();
    
        response.setStatus(HttpServletResponse.SC_OK);
        request.setAttribute("empList", employees);
        request.setAttribute("header", "Company Roster");
        request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        EmployeeService es = new EmployeeService();
        int empId = Integer.parseInt(request.getParameter("empid"));
        
        Employee emp = es.searchEmployeeById(empId);
        emp.setRoles(new HashSet<Role>());
        es.updateEmployee(emp);
        es.deleteEmployee(emp);
        
        doGet(request, response);
    }

}
