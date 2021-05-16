<!DOCTYPE html>
<%@include file="pageCommon.jsp" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
    <sec:authentication property="principal.username" var="currentUser"/>
</sec:authorize>
<h1>Index: Hello ${currentUser}</h1>

</body>
</html>