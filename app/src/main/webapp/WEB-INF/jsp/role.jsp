<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import = "com.ecc.ems.Role" %>  
<%@ page import = "java.util.List" %>    
<%@ page import = "java.util.ArrayList" %>  
<%@ page import = "java.util.Set" %> 

<!DOCTYPE html>
<html>  
  <head>
    <title>Employee Management System</title>
    <link type="text/css" rel="stylesheet" href="../css/materialize.min.css" />
    <link type="text/css" rel="stylesheet" href="../css/app.css" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  
    <script src="../js/jquery-3.1.1.js"></script>
    
    <script src="../js/materialize.min.js"></script>
  </head>
  
  <body>    
    <jsp:include page = "header.jsp" />
    <main>
        <div class="row spacer"></div>
        <div class="row">
          <div class="col m4 l4"></div>
          <div class="col m4 l4">
            <table class="bordered highlight" >
              <thead>
                <tr>
                  <th>Role</th>
                  <th>Assigned Employees</th>
                  <th></th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <%
                    List<Role> roles = (List<Role>) request.getAttribute("roles");
                    
                    if(roles != null){
                        for(Role role : roles) {
                %>
                            <tr>
                                <td><%= role.getName()%></td>
                                <td><%= role.getEmployees().size()%></td>
                                <td>
                                  <button class="btn waves-effect waves-light btn-icon blue accent-2 role_edit" data-roleid="<%= role.getId() %>" data-roleName="<%= role.getName() %>">
                                    <i class="material-icons small">mode_edit</i>
                                  </button>
                                </td>
                                <td>
                                  <form method="POST">
                                    <input type="hidden" name="roleid_delete" value="<%= role.getId() %>">                    
                                    <button class="btn waves-effect waves-light btn-icon red lighten-1 delete" type="submit" name="action">
                                      <i class="material-icons small">delete</i>
                                    </button>
                                  </form>
                                </td>
                            </tr>
                <%
                        }
                    } 
                %>
              </tbody>
              
            </table>
          </div>
          <div class="fixed-action-btn toolbar">
            <a class="btn-floating btn-large waves-effect waves-light red modal-trigger btn" href="#roleModal">
              <i class="material-icons">add</i>
            </a>
          </div>
          
          <div id="roleModal" class="modal">
            <form method="POST">
              <div class="modal-content">
                <h4>Add Role</h4>
                
                  <div class="row">
                    <div class="col s6 m6 l6">
                      Add new role:
                      <div class="input-field inline">
                        <input id="newRole" name="newRole" class="validate">
                        <input name="newRoleId" type="hidden">
                      </div>
                    </div>
                  </div>
              </div>
              <div class="modal-footer">
                <input class="modal-action modal-close waves-effect waves-green btn-flat" type="submit" value="Submit"/>
                <a href="" class="modal-close waves-effect waves-green btn-flat">Cancel</a>
              </div>
            </form>
          </div>
        </div>
    </main>
    
    <script type="text/javascript" src="../js/app.js"></script>
  </body> 
</html>

 
