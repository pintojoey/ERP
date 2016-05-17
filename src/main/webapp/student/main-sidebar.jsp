
 <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="../dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p><%=request.getSession().getAttribute("name")%></p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
      <!-- search form -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="header">MAIN NAVIGATION</li>
        <li>
          <a href="home.jsp" >
            <i class="fa fa-home"></i> <span>Home</span> 
          </a>
          
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-book"></i> <span>My Tests</span> <i class="fa fa-angle-left pull-right"></i>
          </a>
          <ul class="treeview-menu">
            <li><a href="testpaperlist.jsp"><i class="fa fa-circle-o"></i>All Tests</a></li>
            <li><a href="solutionList.jsp"><i class="fa fa-circle-o"></i>My Test</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-book"></i> <span>Forms</span> <i class="fa fa-angle-left pull-right"></i>
          </a>
          <ul class="treeview-menu">
            <li><a href="forms.jsp"><i class="fa fa-circle-o"></i>All Forms</a></li>
            <li><a href="selectFields.jsp"><i class="fa fa-circle-o"></i>Create New Form</a></li>
            <li><a href="fillFields.jsp?id1=Bonafide%20Form"><i class="fa fa-circle-o"></i>Bonafide</a></li>
          </ul>
        </li>
       
        <li class="treeview">
          <a href="#">
            <i class="fa fa-cogs"></i> <span>My Account</span> <i class="fa fa-angle-left pull-right"></i>
          </a>
          <ul class="treeview-menu">
            <li><a href="../resetPassword.jsp"><i class="fa fa-circle-o"></i>Change Password</a></li>
            <li><a href="myProfile.jsp"><i class="fa fa-circle-o"></i>My Profile</a></li>
          </ul>
        </li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

