<%@ tag import="uk.ac.gre.cw.aircraft.services.QualificationService" %>
<%@ tag import="uk.ac.gre.cw.aircraft.entities.Qualification" %>
<%@ tag import="java.util.Collection" %>
<%@tag pageEncoding="UTF-8" %>
<div class="form-group">
  <label for="selQualification" class="col-sm-2 control-label">Qualification</label>
  <div class="col-xs-4">
    <select class="form-control" id="selQualification">
      <%
        StringBuffer sb = new StringBuffer();
        QualificationService qualificationService = new QualificationService();
        Collection<Qualification> qualifications = qualificationService.findAll();
        if (qualifications!=null && qualifications.size() > 0) {
          for (Qualification qualification: qualifications) {
            %><option value="<%=qualification.getId()%>"><%=qualification.getName()%></option><%
          }
        }
      %>
    </select>
  </div>
</div>