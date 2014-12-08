<%@tag pageEncoding="UTF-8" %>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="<%=request.getContextPath()%>">Aircraft v1.0</a>
    </div>
    <!-- /.navbar-header -->

    <ul class="nav navbar-top-links navbar-right">
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li><a href="#" id="user-profile"><i class="fa fa-user fa-fw"></i> User Profile</a>
                </li>

                <li class="divider"></li>
                <li><a href="<%=request.getContextPath()%>handler/logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                </li>
            </ul>
            <!-- /.dropdown-user -->
        </li>
        <!-- /.dropdown -->
    </ul>
    <!-- /.navbar-top-links -->

    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">
                <li class="sidebar-search">
                    <div class="input-group custom-search-form">
                        <input type="text" class="form-control" placeholder="Search...">
                                 <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                             </span>
                    </div>
                    <!-- /input-group -->
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/"><i class="fa fa-home fa-fw"></i> Home <span class="fa arrow"></span></a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/user"><i class="fa fa-users fa-fw"></i> Engineers <span class="fa arrow"></span></a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/job"><i class="fa fa-wrench fa-fw"></i> Jobs <span class="fa arrow"></span></a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/qualification"><i class="fa fa-sitemap fa-fw"></i> Qualifications <span class="fa arrow"></span></a>
                </li>

            </ul>
        </div>
        <!-- /.sidebar-collapse -->
    </div>
    <!-- /.navbar-static-side -->
</nav>