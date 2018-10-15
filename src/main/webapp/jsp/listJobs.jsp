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
        <h1>LIST OF JOBS</h1>
        <div align="right"><html:form action="/seekerhomepage" > <html:submit value="HOME" /> </html:form></div>
        <c:choose>
        <c:when test="${JobList.size()==0}">
        <h2 align="center"> NO JOBS TO DISPLAY </h2>
        </c:when>
        <c:otherwise>
        <table>
            <tr>
                <th> JOB TITLE </th>
                <th> STATUS </th>
                <th> START DATE TIME </th>
                <th> END DATE TIME </th>
                <th> PAY PER HOUR </th>
            </tr>
            <c:forEach items="${JobList}" var="Job">
            <tr>
                <td><c:out value="${Job.jobTitle}" /></td>
                <td><c:out value="${Job.status}" /></td>
                <td><c:out value="${Job.startDateTime}" /></td>
                <td><c:out value="${Job.endDateTime}" /></td>
                <td><c:out value="${Job.payPerHour}" /></td>
                <td>
                    <html:form action="/seeker/editjob.do" method="post" >
                    <html:hidden property="id" value="${Job.id}" />
                    <html:submit value="Edit job" />
                    </html:form>
                </td>
                <td>
                    <html:form action="/seeker/listapplications.do" method="post" >
                    <html:hidden property="id" value="${Job.id}" />
                    <html:submit value="List applications" />
                    </html:form>
                </td>
                <td>
                    <html:form action="/seeker/closejob.do" method="post" >
                    <html:hidden property="id" value="${Job.id}" />
                    <html:submit value="Close Job" />
                    </html:form>
                </td>
            </tr>
            </c:forEach>
        </table>
        </c:otherwise>
        </c:choose>
    </body>
</html>