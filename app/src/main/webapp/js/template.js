<script id="list" type="text/template">    
    <%
      List<Employee> employees = (List<Employee>) request.getAttribute("empList"); 
    %>
    <div class="col s12 m12 l12">
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
            </td>
            <td>
              <i class="material-icons small btn waves-effect waves-light btn-icon green darken-2 edit" data-empid="<%= emp.getId() %>">
                  mode_edit
              </i>
              <i class="material-icons small btn waves-effect waves-light btn-icon red darken-2 delete" data-empid="<%= emp.getId() %>">
                  delete
              </i>
            </td>
          </tr>
          <%      } 
              }
          %>
        </tbody>
      </table>
    </div> 
</script>
