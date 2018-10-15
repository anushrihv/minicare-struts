<%@page import="java.util.*,com.minicare.model.MemberModel" %>
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

</style>

<html>
    <body>
    <c:if test="${CurrentUser.type=='SITTER'}">
        <div align="right"><html:form action="/sitterhomepage" > <html:submit value="HOME" /> </html:form></div>
    </c:if>
    <c:if test="${CurrentUser.type=='SEEKER'}">
       <div align="right"><html:form action="/seekerhomepage" > <html:submit value="HOME" /> </html:form></div>
    </c:if>
    <c:choose>
        <c:when test="${SearchResultSet.size()==0}">
        <h3 align="center">No such email found</h3>
        </c:when>
        <c:otherwise>
        <table>
            <tr>
                <th>FIRST NAME</th>
                <th>LAST NAME</th>
                <th>EMAIL</th>
                <th>TYPE</th>
                <th>PHONE NUMBER</th>
            </tr>
            <c:forEach items="${SearchResultSet}" var="member">
            <tr>
                <td><c:out value="${member.firstName}" /></td>
                <td><c:out value="${member.lastName}" /></td>
                <td><c:out value="${member.email}" /></td>
                <td><c:out value="${member.type}" /></td>
                <td><c:out value="${member.phoneNumber}" /></td>
            </tr>
            </c:forEach>
        </table>
        </c:otherwise>
    </c:choose>
    </body>
</html>