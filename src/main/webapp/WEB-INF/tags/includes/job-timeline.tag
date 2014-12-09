<%@ tag import="uk.ac.gre.cw.aircraft.services.UserService" %>
<%@ tag import="uk.ac.gre.cw.aircraft.entities.User" %>
<%@ tag import="uk.ac.gre.cw.aircraft.entities.Role" %>
<%@ tag import="java.util.Collection" %>
<%@ tag import="uk.ac.gre.cw.aircraft.entities.Job" %>
<%@ tag import="uk.ac.gre.cw.aircraft.services.JobService" %>
<%@ tag import="com.google.gson.Gson" %>
<%@ tag import="uk.ac.gre.cw.aircraft.entities.Engineer" %>
<%@tag pageEncoding="UTF-8" %>
<%
  User currentUser = UserService.getCurrentUser(session);
  if (currentUser == null) {
      //response.sendRedirect(request.getContextPath() + "/login");
  } else {
    if (currentUser.containRole(Role.ENGINEER)) {
      Gson gson = new Gson();
      JobService jobService = new JobService();
      Collection<Job> jobs = jobService.findByEngineer(gson.fromJson(gson.toJson(currentUser), Engineer.class));
      if (jobs != null && jobs.size() > 0) {
        %><ul class="timeline"><%
      int count = 0;
        for (Job job : jobs) {
%>

  <li <%=count % 2 == 0 ? "class=\"timeline-inverted\"" : ""%>>
    <div class="timeline-badge" title="<%=job.getPriority().getName()%>" style="color:<%=job.getPriority().getColor()%>;background: white;border: 1px solid;"><i class="fa <%=job.getPriority().getIcon()%>"></i>
    </div>
    <div class="timeline-panel">
      <div class="timeline-heading">
        <h4 class="timeline-title"><%=job.getName()%></h4>
        <p><i class="fa fa-calendar"></i> <%=job.getStrCreatedDate()%></p>
      </div>
      <div class="timeline-body">
        <p><%=job.getDescription()%></p>
      </div>
    </div>
  </li>
<%
    count++;
  }
  %></ul><%
      }
      else {
        %><h2>No available job</h2><%
      }
    }
  }
%>