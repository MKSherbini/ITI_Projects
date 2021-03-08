<html>
<body>
<jsp:useBean scope="request" id="user" class="lab5.models.User"/>
<jsp:setProperty name="user" property="*"/>
<jsp:forward page="ShowBeans.jsp"/>
</body>
</html>
