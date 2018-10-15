<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>

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

.right{
float : right ;
}
</style>


<html>
    <body>
        <h1>Home Page</h1>
        <div align="center" >
        <logic:messagesPresent message="true">
                <html:messages id="HomePageMessage" message="true">
                   <h4 style="color:green"><bean:write name="HomePageMessage"/><h4>
                </html:messages>
        </logic:messagesPresent>
        </div>
        <form action="/minicare-1.0-SNAPSHOT/member/logout.do" class="right"><input type="submit" value="LOG OUT"></form>
        <form action="/minicare-1.0-SNAPSHOT/sitter/showjob.do "><input type="submit" value="SHOW JOB"></form>
        <form action="/minicare-1.0-SNAPSHOT/sitter/editaccountform.do " class="right"><input type="submit" value="EDIT ACCOUNT"></form>
        <form action="/minicare-1.0-SNAPSHOT/sitter/listjobapplications.do "><input type="submit" value="JOB APPLICATIONS"></form>
        <form action="/minicare-1.0-SNAPSHOT/member/resetpasswordform.do" class="right"><input type="submit" value="RESET PASSWORD"></form>
        <form action="/minicare-1.0-SNAPSHOT/sitter/closeaccount.do"><input type="submit" value="CLOSE ACCOUNT"></form>
        <form action="/minicare-1.0-SNAPSHOT/member/searchform.do" class="right"><input type="submit" value="SEARCH MEMBER"></form>

    </body>
</html>
