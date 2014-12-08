<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="include" tagdir="/WEB-INF/tags/includes" %>
<t:less pageTitle="Aircraft System">
  <div class="container">
    <div class="row">
      <div class="col-md-4 col-md-offset-4">
        <div class="login-panel panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">Aircraft System</h3>
          </div>
          <div class="panel-body">
            <include:error-message></include:error-message>
            <include:login-form></include:login-form>
          </div>
        </div>
      </div>
    </div>
  </div>
</t:less>
