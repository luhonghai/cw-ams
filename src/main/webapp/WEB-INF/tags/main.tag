<%@ tag import="uk.ac.gre.cw.aircraft.entities.User" %>
<%@ tag import="uk.ac.gre.cw.aircraft.Common" %>
<%@ tag import="uk.ac.gre.cw.aircraft.services.UserService" %>
<%@ tag import="uk.ac.gre.cw.aircraft.entities.Role" %>
<%@tag description="Main screen" pageEncoding="UTF-8" %>
<%@taglib prefix="include" tagdir="/WEB-INF/tags/includes" %>
<%@attribute name="pageTitle" required="true" %>
<%@attribute name="requireAdminRole" %>
<%
    try {
        User currentUser = UserService.getCurrentUser(session);
        if (Common.DEBUG || currentUser != null) {
            if (requireAdminRole != null && requireAdminRole.equalsIgnoreCase("true") && !currentUser.containRole(Role.ADMINISTRATOR)) {
                response.sendRedirect(request.getContextPath() + "/");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        response.sendRedirect(request.getContextPath() + "/");
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

