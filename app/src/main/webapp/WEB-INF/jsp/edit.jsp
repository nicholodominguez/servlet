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
      <div class="container">
        <div class="row spacer"></div>
        <div id="view" class="row">
          <%
          Employee emp = (Employee) request.getAttribute("employee");
          SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
          %>
          
          <form method="POST" class="col s12">
            <div class="row">
              <input name="empId" type="hidden" value="<%= emp!=null?emp.getId():"" %>">
              <div class="col l6 m6 s12">  
                <h4 class="teal-text text-darken-2"><i class="material-icons prefix">perm_identity</i> Name</h4>
                <div class="row">
                  <div class="input-field col s10">
                    <input value="<%= emp!=null?(emp.getName().getTitle() == null ? "" : emp.getName().getTitle()):"" %>" name="title" type="text" class="validate">
                    <label class="active" for="title">Title</label>
                  </div>
                </div>
                <div class="row">
                  <div class="input-field col s10">
                    <input value="<%= emp!=null?(emp.getName().getFirstname()):"" %>" name="firstname" type="text" class="validate" required="" aria-required="true">
                    <label class="active" for="firstname">First Name</label>
                  </div>
                </div>
                <div class="row">
                  <div class="input-field col s10">
                    <input value="<%= emp!=null?(emp.getName().getMiddlename()):"" %>" name="middlename" type="text" class="validate" required="" aria-required="true">
                    <label class="active" for="middlename">Middle Name</label>
                  </div>
                </div>
                <div class="row">
                  <div class="input-field col s10">
                    <input value="<%= emp!=null?(emp.getName().getLastname()):"" %>" name="lastname" type="text" class="validate" required="" aria-required="true">
                    <label class="active" for="lastname">Last Name</label>
                  </div>
                </div>
                <div class="row">  
                  <div class="input-field col s10">
                    <input value="<%= emp!=null?(emp.getName().getSuffix() == null ? "" : emp.getName().getSuffix()):"" %>" name="suffix" type="text" class="validate">
                    <label class="active" for="suffix">Suffix</label>
                  </div>
                </div>
              </div>
              <div class="col l6 m6 s12">  
                <h4 class="teal-text text-darken-2"><i class="material-icons prefix">business</i> Address</h4>
                <div class="row">
                  <div class="input-field col s10">
                    <input value="<%= emp!=null?(emp.getAddress().getStreet() == null ? "" : emp.getAddress().getStreet()):"" %>" name="street" type="text" class="validate" required="" aria-required="true">
                    <label class="active" for="street">Street</label>
                  </div>
                </div>
                <div class="row">
                  <div class="input-field col s10">
                    <input value="<%= emp!=null?(emp.getAddress().getBrgy() == null ? "" : emp.getAddress().getBrgy()):"" %>" name="brgy" type="text" class="validate" required="" aria-required="true">
                    <label class="active" for="brgy">Brgy</label>
                  </div>
                </div>
                <div class="row">
                  <div class="input-field col s10">
                    <input value="<%= emp!=null?(emp.getAddress().getMunicipality() == null ? "" : emp.getAddress().getMunicipality()):"" %>" name="municipality" type="text" class="validate" required="" aria-required="true">
                    <label class="active" for="municipality">Municipality</label>
                  </div>
                </div>
                <div class="row">
                  <div class="input-field col s10">
                    <input value="<%= emp!=null?(emp.getAddress().getCountry() == null ? "" : emp.getAddress().getCountry()):"" %>" name="country" type="text" class="validate" required="" aria-required="true">
                    <label class="active" for="country">Country</label>
                  </div>
                </div>
                <div class="row">  
                  <div class="input-field col s10">
                    <input value="<%= emp!=null?(emp.getAddress().getZipcode() == null ? "" : emp.getAddress().getZipcode()):"" %>" name="zipcode" type="text" class="validate" required="" aria-required="true">
                    <label class="active" for="zipcode">Zipcode</label>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="input-field col s4">
                  <input value="<%= emp!=null?(emp.getBdate() == null ? "" : dateFormat.format(emp.getBdate())):"" %>" name="birthdate" type="date" class="datepicker" required="" aria-required="true">
                  <label class="active" for="birthdate">Birthdate</label>
                </div>
                <div class="input-field col s2">
                  <input value="<%= emp!=null?(emp.getGwa() == null ? "" : emp.getGwa()):"" %>" class="validate" name="gwa" type="number" step="0.01" placeholder="1.0" required="" aria-required="true">
                  <label class="active" for="gwa">General Weighted Average</label>
                </div>
                <div class="input-field col s4">
                  <input value="<%= emp!=null?(emp.getDateHired() == null ? "" : dateFormat.format(emp.getDateHired())):"" %>" name="dateHired" type="date" class="datepicker" required="" aria-required="true">
                  <label class="active" for="dateHired">Date Hired</label>
                </div>
              </div>
              <div class="row">
                <h5 class="teal-text text-darken-2"><i class="material-icons prefix">contacts</i> Contacts</h5>
                <div class="input-field col s6">
                  <div id="contact_list">
                    <%
                        if(emp != null){
                            if(emp.getContacts() != null){
                              List<Contact> contacts = new ArrayList<Contact>(emp.getContacts());
                              for(Contact contact : contacts){ %>
                                <div class="row">
                                  <div class="input-field col s7">
                                    <input id="contact_<%= contact.getId()%>" name="contact_details" value="<%= contact.getContactDetails() %>">
                                    <input type="hidden" name="contact_id" value="<%= contact.getId() %>">
                                    <input type="hidden" name="contact_type" value="<%= contact.getContactType() %>">
                                    <label class="active" for="contact_<%= contact.getId()%>"><%= contact.getContactType() %></label>
                                  </div>
                                  <div class="col s2">
                                    <i class="material-icons small btn waves-effect waves-light btn-icon red lighten-1 contact_delete" data-contactid="<%= contact.getId()%>">
                                        delete
                                    </i>
                                  </div>
                                </div>
                              <% }
                            }
                         }
                    %>
                  </div>
                  <div class="row">
                    <div class="col s4">
                      <a class="waves-effect waves-light btn add_contact blue accent-2" id="Mobile"><i class="material-icons left">add</i> Mobile</a>
                    </div>
                    <div class="col s4">
                      <a class="waves-effect waves-light btn add_contact blue accent-2" id="Phone"><i class="material-icons left">add</i> Phone</a>
                    </div>
                    <div class="col s4">
                      <a class="waves-effect waves-light btn add_contact blue accent-2" id="Email"><i class="material-icons left">add</i> Mail</a>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row">
                <h5 class="teal-text text-darken-2"><i class="material-icons prefix">assignment_ind</i> Roles</h5>
                <div class="input-field col s6">
                  <%
                      
                      List<Role> availRoles = (List<Role>) request.getAttribute("roles");
                      
                      if(emp != null){
                          Set<Role> roles = emp.getRoles();
                          
                          if(roles != null){
                            for(Role role : roles){ %>
                              <p>
                                <input type="checkbox" name="roles" id="<%= role.getId() %>" value="<%= role.getId() %>" checked="checked">
                                <label for="<%= role.getId() %>"><%= role.getName() %></label>
                              </p>
                         <% }
                          }
                      } 
                      if(availRoles != null){
                        for(Role role : availRoles){ %>
                          <p>
                            <input type="checkbox" name="roles" id="<%= role.getId() %>" value="<%= role.getId() %>">
                            <label for="<%= role.getId() %>"><%= role.getName() %></label>
                          </p>
                     <% }
                      }
                  %>
                  <p></p>
                </div>
              </div>
              <div class="row">
                <div class="col s2">
                  <input class="btn-large waves-effect waves-light blue accent-2" type="submit" value="Submit"/>
                </div>
                <div class="col s2">
                  <a href="" class="btn-large waves-effect waves-light red accent-2">Cancel</a>
                </div>
              </div>
            </div>
          </form>
          
        </div>
      </div>
    </main>
    
    <script type="text/javascript" src="../js/materialize.min.js"></script>
    <script type="text/javascript" src="../js/app.js"></script>
  </body> 
</html>

 
