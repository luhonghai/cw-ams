<%@tag description="Less footer" pageEncoding="UTF-8" %>
<%@taglib prefix="include" tagdir="/WEB-INF/tags/includes" %>
<%@attribute name="pageTitle" required="true" %>
<!DOCTYPE html>
<html lang="en">
<include:head>
    <jsp:attribute name="pageTitle"><%=pageTitle%></jsp:attribute>
</include:head>
<body>
<jsp:doBody/>
<include:after-footer></include:after-footer>
</body>
</html>

