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
    border-radius: 10px;
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
        <div align="right">
        <form action="/minicare-1.0-SNAPSHOT/jsp/seeker_homepage.jsp" class="button"> <input type="submit" value="HOME" > </form>
        </div>
        <c:choose>
        <c:when test="${JobApplicationList.size()==0}">
        <h2 align="center"> NO APPLICATIONS TO DISPLAY </h2>
        </c:when>
        <c:otherwise>
        <table>
            <tr>
                <th> JOB TITLE </th>
                <th> FIRST NAME </th>
                <th> LAST NAME </th>
                <th> EXPECTED PAY </th>
            </tr>
            <c:forEach items="${JobApplicationList}" var="Job">
            <tr>
               <td><c:out value="${Job.jobTitle}" /></td>
               <td><c:out value="${Job.sitterFirstName}" /></td>
               <td><c:out value="${Job.sitterLastName}" /></td>
               <td><c:out value="${Job.expectedPay}" /></td>
            </tr>
            </c:forEach>
        </table>
        </c:otherwise>
        </c:choose>
    </body>
</html>