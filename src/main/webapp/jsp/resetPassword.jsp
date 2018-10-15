<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<style>
 input[type=submit] {
    background-color: #555555; /*black*/
    border: none;
    color: white;
    padding: 16px 32px;
    text-decoration: none;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 12px;
}
h1{
text-align : center;
}

.error{ color:red; }
</style>

<html>
<body>
        <c:if test="${CurrentUser.type=='SITTER'}">
            <div align="right"><html:form action="/sitterhomepage" > <html:submit value="HOME" /> </html:form></div>
        </c:if>
        <c:if test="${CurrentUser.type=='SEEKER'}">
           <div align="right"><html:form action="/seekerhomepage" > <html:submit value="HOME" /> </html:form></div>
        </c:if>
    <div align="center">
        <html:form action="/member/resetpassword.do" method="post">
            <table>
                <tr>
                    <td>Enter old password</td>
                    <td> <html:password property="oldpassword" /> </td>
                    <td class="error"> <html:errors property="OldPasswordError" /> </td>
                </tr>
                <tr>
                    <td>Enter new password</td>
                    <td> <html:password property="newpassword" /> </td>
                    <td class="error"> <html:errors property="NewPasswordError" /> </td>
                </tr>
                <tr>
                    <td>Re-enter new password</td>
                    <td> <html:password property="newpassword2" /> </td>
                    <td class="error"> <html:errors property="NewPassword2Error" /> </td>
                </tr>
                <tr>
                <td><html:submit value="Reset Password" /></td>
                </tr>
            </table>
        </html:form>
    </div>
   </body>
</html>