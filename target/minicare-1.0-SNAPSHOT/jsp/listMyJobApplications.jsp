
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>

<style>
input[type=submit] {
    background-color: #555555; /*black*/
    border: none;
    color: white;
    padding: 10px 25px;
    text-decoration: none;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 12px;
}
table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    padding: 8px;
    text-align: center;
    border-bottom: 1px solid #ddd;
}
h1{
text-align:center
}
</style>


<html>
    <body>
        <h1> JOB APPLICATIONS </h1>
        <div align="right"><html:form action="/sitterhomepage" > <html:submit value="HOME" /> </html:form></div>
        <c:choose>
        <c:when test="${MyJobApplicationList.size()==0}">
        <h2 align="center"> NO JOBS APPLICATIONS TO DISPLAY </h2>
        </c:when>
        <c:otherwise>
        <table>
            <tr>
                <th> TITLE </th>
                <th> EXPECTED PAY </th>
                <th> PAY PER HOUR </th>
                <th> STATUS </th>
            </tr>
            <c:forEach items="${MyJobApplicationList}" var="Job">
            <tr>
                 <td><c:out value="${Job.job.jobTitle}" /></td>
               <td><c:out value="${Job.expectedPay}" /></td>
               <td><c:out value="${Job.job.payPerHour}" /></td>
               <td><c:out value="${Job.status}" /></td>
                <td>
                <html:form action="/sitter/deletejobapplication.do" method="post">
                   <html:hidden property="id" value="${Job.job.id}" />
                   <html:submit value="Delete" />
                </html:form>
                </td>
            </tr>
            </c:forEach>
        </table>
        </c:otherwise>
        </c:choose>
    </body>
</html>