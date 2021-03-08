<html>
<body>
<jsp:useBean scope="request" id="user" class="lab5.models.User"/>
<jsp:getProperty name="user" property="userName"/>
<jsp:getProperty name="user" property="password"/>

</body>
</html>
