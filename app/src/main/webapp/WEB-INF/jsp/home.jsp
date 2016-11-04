<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import = "com.ecc.ems.Employee" %>  
<%@ page import = "com.ecc.ems.Contact" %>  
<%@ page import = "com.ecc.ems.Role" %>  
<%@ page import = "java.util.List" %>    
<%@ page import = "java.util.ArrayList" %> 
<%@ page import = "java.util.Date" %> 
<%@ page import = "java.text.SimpleDateFormat" %>   
<%@ page import = "java.util.Set" %> 

<!DOCTYPE html>
<html>  
  <head>
    <title>Employee Management System</title>
    <link type="text/css" rel="stylesheet" href="../css/materialize.min.css" />
    <link type="text/css" rel="stylesheet" href="../css/app.css" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  
    <script src="../js/jquery-3.1.1.js"></script>
  </head>
  
  <body>    
    <jsp:include page = "header.jsp"/>
    <main>
        <div class="row spacer"></div>
        <div class="row">
          <div class="col s1 m1 l1"></div>
          <div class="col s10 m10 l10">
          <%
          List<Employee> employees = (List<Employee>) request.getAttribute("empList"); 
          %>
            <table class="bordered highlight" >
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Address</th>
                  <th>Birthday</th>
                  <th>Contacts</th>
                  <th>GWA</th>
                  <th>Date Hired</th>
                  <th>Roles</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>  
                <%  if(employees != null){
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
                        for (Employee emp : employees) { 
                %>
                <tr>
                  <td>
                    <%= emp.getName().fullname() %>
                  </td>
                  
                  <td>
                    <%= emp.getAddress().toString() %>
                  </td>
                  
                  <td>
                    <%= dateFormat.format(emp.getBdate()) %>
                  </td>
                  <td>
                  <%
                            if(emp.getContacts() != null){
                                for (Contact contact: new ArrayList<Contact>(emp.getContacts())){
                  %>
                                   <%= contact.stringify()%>;<br/>
                  <%
                                }
                            }
                  %>
                  </td>
                  <td>
                    <%= emp.getGwa() %>
                  </td>
                  <td>
                    <%= dateFormat.format(emp.getDateHired()) %>
                  </td>
                  <td>
                  <%
                            if(emp.getRoles() != null){
                                List<Role> roles = new ArrayList<Role>(emp.getRoles());
                                for (Role role : roles){
                  %>
                                   <%= role.getName() %>
                                   <%
                                    if(roles.size() > 1) {    
                                   %>
                                   , <br/>
                  <%  
                                    }
                                }
                            }
                  %>
                  </td>
                  <td>
                  
                    <button class="btn waves-effect waves-light btn-icon blue accent-2 edit" data-empid="<%= emp.getId() %>">
                      <i class="material-icons small">mode_edit</i>
                    </button>
                    <form method="POST">
                      <input type="hidden" name="empid" value="<%= emp.getId() %>">                    
                      <button class="btn waves-effect waves-light btn-icon red lighten-1 delete" type="submit" name="action">
                        <i class="material-icons small">delete</i>
                      </button>
                    </form>
                  </td>
                </tr>
                <%      } 
                    }
                %>
              </tbody>
              
            </table>
            
            <div class="fixed-action-btn toolbar">
              <a class="btn-floating btn-large waves-effect waves-light red edit" data-empid="">
                <i class="material-icons">add</i>
              </a>
            </div>
          </div>
        </div>
    </main>
    
    <script type="text/javascript" src="../js/materialize.min.js"></script>
    <script type="text/javascript" src="../js/app.js"></script>
  </body> 
</html>

 
