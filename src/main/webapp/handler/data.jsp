<%@ page import="uk.ac.gre.cw.aircraft.services.AbstractService" %>
<%@ page import="uk.ac.gre.cw.aircraft.services.QualificationService" %>
<%@ page import="uk.ac.gre.cw.aircraft.services.JobService" %>
<%@ page import="uk.ac.gre.cw.aircraft.services.UserService" %>
<%@ page import="java.util.Collection" %>
<%@ page import="uk.ac.gre.cw.aircraft.entities.Qualification" %>
<%@ page import="uk.ac.gre.cw.aircraft.entities.Job" %>
<%@ page import="uk.ac.gre.cw.aircraft.entities.User" %>
<%@ page import="uk.ac.gre.cw.aircraft.hanlder.TableData" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.io.PrintWriter" %>
<%
  String target = request.getParameter("target");
  String type = request.getParameter("type");
  String data = request.getParameter("data");
  String option = request.getParameter("option");
  AbstractService service = null;
  Gson gson = new Gson();
  PrintWriter writer  = response.getWriter();
  User currentUser = UserService.getCurrentUser(session);
  if (target == null || target.length() == 0 || type == null || type.length() == 0) {
    TableData tData = new TableData();
    tData.setMessage("Wrong parameter. Target: " + target + ". Type: " + type);
    writer.print(gson.toJson(tData));
  }
  try {
      if (target.equalsIgnoreCase("qualification")) {
        service = new QualificationService();
        Qualification qualification = null;
        if (data!=null && data.length() > 0) {
          System.out.println(data);
            qualification = gson.fromJson(data, Qualification.class);
        }
        TableData<Qualification> tableData = new TableData<Qualification>();
        if (type.equalsIgnoreCase("list")) {
          Collection<Qualification> collection = service.findAll();
          tableData.setData(collection);
          tableData.addColumn(new TableData.Column("ID", "id"));
          tableData.addColumn(new TableData.Column("Name", "name"));
          tableData.addColumn(new TableData.Column("Description", "description"));
          tableData.addColumn(new TableData.Column("Created date", "strCreatedDate"));
        } else if (type.equalsIgnoreCase("delete")) {
          service.delete(qualification);
        } else if (type.equalsIgnoreCase("update")) {
          qualification = (Qualification) service.update(qualification);
          tableData.setObject(qualification);
        } else if (type.equalsIgnoreCase("create")) {
          qualification = (Qualification) service.create(qualification);
          tableData.setObject(qualification);
        }
        tableData.setMessage("Completed");
        writer.print(gson.toJson(tableData));
      } else if (target.equalsIgnoreCase("job")) {
        Job job = null;
        TableData<Job> tableData = new TableData<Job>();
        if (data!=null && data.length() > 0) {
           job = gson.fromJson(data, Job.class);
        }
        service = new JobService();
        if (type.equalsIgnoreCase("list")) {
          Collection<Job> collection = service.findAll();
          tableData.setData(collection);
          tableData.addColumn(new TableData.Column("ID", "id"));
          tableData.addColumn(new TableData.Column("Name", "name"));
          tableData.addColumn(new TableData.Column("Description", "description"));
          tableData.addColumn(new TableData.Column("Qualification", "qualification.name"));
          tableData.addColumn(new TableData.Column("Priority", "priority.name"));
          tableData.addColumn(new TableData.Column("Status", "status.name"));
          tableData.addColumn(new TableData.Column("Engineer number", "numberOfEngineer"));
          tableData.addColumn(new TableData.Column("Created date", "strCreatedDate"));
        } else if (type.equalsIgnoreCase("delete")) {
          service.delete(job);
        } else if (type.equalsIgnoreCase("create")) {
          job = (Job) service.create(job);
          tableData.setObject(job);
        } else if (type.equalsIgnoreCase("update")) {
          job = (Job) service.update(job);
          tableData.setObject(job);
        }
        tableData.setMessage("Completed");
        writer.print(gson.toJson(tableData));
      } else if (target.equalsIgnoreCase("user")) {
        TableData<User> tableData = new TableData<User>();
        User user = null;
        if (data != null && data.length() > 0) {
          user = gson.fromJson(data, User.class);
        }
        service = new UserService();
        if (type.equalsIgnoreCase("list")) {
          Collection<User> collection = service.findAll();

          tableData.setData(collection);
          tableData.addColumn(new TableData.Column("ID", "id"));
          tableData.addColumn(new TableData.Column("First Name", "firstName"));
          tableData.addColumn(new TableData.Column("Last Name", "lastName"));
          tableData.addColumn(new TableData.Column("Email", "email"));
          tableData.addColumn(new TableData.Column("Username", "username"));
          tableData.addColumn(new TableData.Column("Gender", "strGender"));
          tableData.addColumn(new TableData.Column("Role", "strRoles"));
          tableData.addColumn(new TableData.Column("Created date", "strCreatedDate"));
        } else if (type.equalsIgnoreCase("delete")) {
          service.delete(user);
        } else if (type.equalsIgnoreCase("create")) {
          user = (User) service.create(user);
          tableData.setObject(user);
        } else if (type.equalsIgnoreCase("update")) {
          if ((option != null && option.length() > 0 && option.equalsIgnoreCase(AbstractService.OPTION_PROFILE))
               ||  user.getUsername().equalsIgnoreCase(currentUser.getUsername()) ) {
            service.setOption(AbstractService.OPTION_PROFILE);
            user = (User) service.update(user);
            session.setAttribute("user", user);
          } else {
            user = (User) service.update(user);
          }
          tableData.setObject(user);
        }
        tableData.setMessage("Completed");
        writer.print(gson.toJson(tableData));
      } else if (target.equalsIgnoreCase("current_user")) {
        writer.print(gson.toJson(session.getAttribute("user")));
      }
  } catch (Exception ex) {
    ex.printStackTrace();
    TableData tData = new TableData();
    tData.setMessage(ex.getMessage());
    writer.print(gson.toJson(tData));
  }

%>