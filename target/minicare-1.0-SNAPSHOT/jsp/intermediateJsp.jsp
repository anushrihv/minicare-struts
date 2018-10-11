<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>

<% String type = request.getParameter("type"); %>
<% boolean b = type.equals("Sitter"); %>
<c:choose>
    <c:when test="b">
        String class = "com.minicare.dto.SitterFormBean";
    </c:when>

    <c:when test="b">
        String class="com.minicare.dto.SeekerFormBean";
    </c:when>
</c:choose>


<jsp:useBean class="class" scope="request" id="FormBean" >
    <jsp:setProperty name="FormBean" property="*" />
</jsp:useBean>

<% application.getRequestDispatcher("/visitor/register.do").forward(request,response); %>