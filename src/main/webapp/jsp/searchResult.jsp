<%@page import="java.util.*,com.minicare.model.MemberModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

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
        <div align="right"><form action="/minicare-1.0-SNAPSHOT/jsp/sitter_homepage.jsp" > <input type="submit" value="HOME" > </form></div>
    </c:if>
    <c:if test="${CurrentUser.type=='SEEKER'}">
       <div align="right"><form action="/minicare-1.0-SNAPSHOT/jsp/seeker_homepage.jsp" > <input type="submit" value="HOME" > </form></div>
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