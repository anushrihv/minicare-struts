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
</style>

<html>
    <body>
        <h3 align="center">ENTER EMAIL TO SEARCH MEMBER </h1>
        <div align="center">
        <html:form action="/member/search.do" method="POST">
            <html:text property="email" />
            <html:submit value="SEARCH" />
        </html:form>
        </div>
    </body>
</html>