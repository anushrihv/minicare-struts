<%@page isErrorPage="true" %>

<html>
    <body>
        <h1 style="color:red">ERROR OCCURRED WHILE LOADING THIS PAGE</h1>
        <%= exception.getMessage() %>
    </body>
</html>