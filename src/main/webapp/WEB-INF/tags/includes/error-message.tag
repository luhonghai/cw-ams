<%@tag pageEncoding="UTF-8" %>
<% Object error = session.getAttribute("error");
    if (error != null) {
%>
<div class="alert alert-danger alert-dismissible" role="alert">
    <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
    <strong>Error!</strong> <%=(String) error%>
</div>
<%
    }
    session.setAttribute("error", null);
%>