<%@ tag import="uk.ac.gre.cw.aircraft.entities.User" %>
<%@ tag import="uk.ac.gre.cw.aircraft.Common" %>
<%@tag description="Main screen" pageEncoding="UTF-8" %>
<%@taglib prefix="include" tagdir="/WEB-INF/tags/includes" %>
<%@attribute name="pageTitle" required="true" %>
<%
    Object loginUser = session.getAttribute("user");
    if (Common.DEBUG || (loginUser != null && loginUser instanceof User)) {

    } else {
        response.sendRedirect(request.getContextPath() + "/login");
    }
%>
<!DOCTYPE html>
<html lang="en">
    <include:head pageTitle="<%=pageTitle%>">
    </include:head>
<body>

    <div id="wrapper">
        <include:navigation></include:navigation>
         <div id="page-wrapper">
                <include:header pageTitle="<%=pageTitle%>"></include:header>
                    <!-- /.row -->
                    <!-- /.row -->
                    <!-- /.panel -->
                    <div class="panel panel-default">
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                <jsp:doBody/>
                            </div>
                        </div>
         </div>
    </div>
    <include:footer></include:footer>
    <include:after-footer></include:after-footer>
</body>
</html>

