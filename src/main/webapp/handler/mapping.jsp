<%@ page import="uk.ac.gre.cw.aircraft.services.UserService" %>
<%@ page import="uk.ac.gre.cw.aircraft.services.ServiceException" %>
<%@ page import="uk.ac.gre.cw.aircraft.hanlder.MappingData" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.google.gson.Gson" %><%
  String mapping = request.getParameter("mapping");
  String id = request.getParameter("id");
  String action = request.getParameter("act");
  MappingData data = new MappingData();
  Gson gson = new Gson();
  PrintWriter writer = response.getWriter();
  if (mapping != null && mapping.length() > 0 && action != null && action.length() > 0
          && id != null && id.length() > 0) {
    try {
      data.setId(Integer.parseInt(id));
      data.setMessage("Completed");
      UserService service = new UserService();
      if (mapping.equalsIgnoreCase("engineer-job")) {
        data = service.findMappingEngineerJob(data);
      } else if (mapping.equalsIgnoreCase("job-engineer")) {
        data = service.findMappingJobEngineer(data);
      } else if (mapping.equalsIgnoreCase("qualification-engineer")) {
        data = service.findMappingQualificationEngineer(data);
      } else if (mapping.equalsIgnoreCase("engineer-qualification")) {
        data = service.findMappingEngineerQualification(data);
      } else {
        data.setMessage("Invalid mapping type");
      }
    } catch (ServiceException e) {
      e.printStackTrace();
      data.setMessage("Could not find mapping");
    }
  } else {
    data.setMessage("Invalid parameters");
  }
  writer.print(gson.toJson(data));
%>