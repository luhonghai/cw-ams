<%@tag description="Main screen" pageEncoding="UTF-8" %>
<%@taglib prefix="include" tagdir="/WEB-INF/tags/includes" %>
<%@attribute name="pageTitle" required="true" %>
<!DOCTYPE html>
<html lang="en">
    <include:head pageTitle="<%=pageTitle%>">
    </include:head>
<body>
    <include:header></include:header>
    <jsp:doBody/>
    <include:footer></include:footer>
    <include:after-footer></include:after-footer>
</body>
</html>

