<%@ page import="uk.ac.gre.cw.aircraft.services.UserService" %>
<%@ page import="uk.ac.gre.cw.aircraft.entities.User" %>
<%@ page import="uk.ac.gre.cw.aircraft.services.ServiceException" %><%
  String username = request.getParameter("username");
  String password = request.getParameter("password");
  UserService service = new UserService();
  try {
    User loginUser = service.login(username, password);
    if (loginUser != null) {
      session.setAttribute("user", loginUser);
      response.sendRedirect(request.getContextPath() + "/");
    } else {
      session.setAttribute("error", "Invalid username or password");
      response.sendRedirect(request.getContextPath() + "/login");
    }
  } catch (ServiceException e) {
    session.setAttribute("error", e.getMessage());
    response.sendRedirect(request.getContextPath() + "/login");
  }

%>