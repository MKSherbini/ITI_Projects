<%@include file="pageCommon.jsp" %>

<html>
<head>
</head>
<body>
<h3>Welcome, Enter The Employee Details</h3>

<form:form method="POST"
           action="${pageContext.servletContext.contextPath}/app/welcome5/submit" modelAttribute="user">
    <table>
        <tr>
            <td><form:label path="username"><spring:message code="field.username"/></form:label></td>
            <td><form:input path="username"/></td>
            <td><form:errors path="username"/></td>

        </tr>
        <tr>
            <td><form:label path="password"><spring:message code="field.password"/></form:label></td>
            <td><form:password path="password"/></td>
            <td><form:errors path="password"/></td>
        </tr>
            <%--        <tr>--%>
            <%--            <td><form:errors path="*"/></td>--%>
            <%--        </tr>--%>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>

<h3>Welcome, Upload file</h3>
<form method="post" action="${pageContext.servletContext.contextPath}/app/welcome5/upload"
      enctype="multipart/form-data">
    File to upload: <input type="file" name="file">
    <input type="submit" value="Upload">
</form>
</body>
</html>