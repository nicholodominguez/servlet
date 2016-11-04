
<header>
  <nav class="top-nav blue accent-2">
    <div class="container">
      <div class="nav-wrapper">
        <a class="page-title"><%= request.getAttribute("header")%></a>
      </div>
    </div>
  </nav>
  <ul id="slide-out" class="side-nav fixed">
    <li>
      <div class="userView">
        <div class="background">
          <img src="../images/office.jpg"/>
        </div>
        <a href=""><img class="circle" src="../images/user.jpg"/></a>
        <a href=""><span class="white-text name">Admin</span></a>
        <a href=""><span class="white-text email">admin@exist.com</span></a>
      </div>
    </li>
    <ul class="collapsible" data-collapsible="accordion">
      <li class="bold">
        <a class="collapsible-header waves-effect waves-teal">Employee Management System</a>
        <div class="collapsible-body">
          <ul>
            <li><a class="edit" data-empid="" >Add Employee</a></li>
            <li><a href="/employee/">View Employee Roster</a></li>
          </ul>
        </div>
      </li>
      <li>
        <a class="collapsible-header waves-effect waves-teal">Role Management System</a>
        <div class="collapsible-body">
          <ul>
            <li><a href="/role/">Add Role</a></li>
            <li><a href="/role/">View Roles</a></li>
          </ul>
        </div>
      </li>
    </ul>
  </ul>
</header>
