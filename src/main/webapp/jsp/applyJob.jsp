
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>

<html>
    <body>
        <h1 text-align="center"> Enter expected pay </h1>
        <div align="center">
        <html:form action="/sitter/applyjob.do" method="post">
            <html:text property="expectedPay" /> <html:errors property="ExpectedPayError" />
            <html:hidden property="memberId" value="${CurrentUser.memberId}" />
            <html:hidden property="id" />
            <html:submit value="Submit" />
        </html:form></div>
    </body>
</html>