<%@ page import="lab5.models.User" %>
<html>
<style>
    table {
        border-collapse: collapse;
        width: 100%;
        border: 1px solid #ddd;
    }

    th,
    td {
        text-align: left;
        padding: 16px;
    }
</style>
<
<div style=" max-width: max-content;margin-top: 50px;margin-left: auto;margin-right: auto;">
    <div style="margin-left: 20px;">
        <h1>Registration form</h1>
    </div>
    <div style="margin-left: 10px;">
        please enter the first name or part of it
    </div>
    <FORM action="search" method="post">
        <BR> <b> FirstName:</b><INPUT style="margin-left: 12px;margin-top: 5px;margin-bottom: 5px;" TYPE=TEXT
                                      NAME=query>
        <INPUT TYPE="SUBMIT" VALUE="Submit">
    </FORM>
</div>

<%
    java.util.List<lab5.models.User> res = (java.util.ArrayList<lab5.models.User>) request.getAttribute("result");
    if (res == null) {
        out.println("null result");
        return;
    }
%>

<table>
    <tr>
        <th>FirstName</th>
        <th>LastName</th>
        <th>Username</th>
        <th>Password</th>
    </tr>
    <%
        for (User user : res) {
    %>
    <tr>
        <th><%=(user.getFirstName())%></th>
        <th><%=(user.getLastName())%></th>
        <th><%=(user.getUserName())%></th>
        <th><%=(user.getPassword())%></th>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
