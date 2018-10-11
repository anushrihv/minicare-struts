<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<style>
.error{
color:red;
}

input[type=submit] {
    background-color: #555555; /* black */
    border: none;
    color: white;
    padding: 16px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 14px;
    border-radius: 12px;
}

</style>

<html>

    <body>

        <h1 align="center">Welcome to Mini-Care</h1>
        <br><br><br><br>
        <div align="center">
        <html:form action="/visitor/login" method="POST">
            <p style="color:green"> ${Message} </p>
            <h2>Login here</h2>
            <table>
                <tr>
                    <td>Email :</td>
                    <td> <html:text property="email" /> </td>
                    <td class="error"><html:errors property="LoginEmailError" /></td>
                </tr>
                <tr>
                    <td>Password :</td>
                    <td> <html:password property="password"  /> </td>
                    <td class="error"><html:errors property="LoginPasswordError" /></td>
                </tr>
            </table>
            <html:submit value="Submit" />
        </html:form></div>

        <br><br><br><br>

        <div align="center">
           <html:form action="/seekerregisterpage">
               <html:submit value="Register as Seeker" />
           </html:form>
           <html:form action="/sitterregisterpage">
               <html:submit value="Register as Sitter" />
           </html:form>
        </div>


    </body>

</html>

