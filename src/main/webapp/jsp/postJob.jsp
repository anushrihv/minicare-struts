<%@page import="java.util.*,com.minicare.model.JobModel" %>
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
h1{
text-align : center;
}

.error{color:red;}

</style>

<html>
    <body>
        <h1>POST JOB</h1>
        <div align="right"><html:form action="/seekerhomepage" > <html:submit value="HOME" /> </html:form></div>
        <div align ="center"><html:form action="/seeker/postjob.do" method="post">
            <table>
                <tr>
                    <td>Job Title : </td>
                    <td> <html:text property="jobTitle" /> </td>
                    <td class="error"> <html:errors property="JobTitleError" /> </td>
                </tr>
                <tr>
                    <td>Start Date Time :</td>
                    <td> <input type="date" name="startDate" value="${JobForm.startDate}" required> </td>
                    <td> <input type="time" name="startTime" value="${JobForm.startTime}" required> </td>
                    <td class="error"> <html:errors property="StartDateTimeError" /> </td>
                </tr>
                <tr>
                    <td>End Date Time :</td>
                    <td> <input type="date" name="endDate" value="${JobForm.endDate}" required> </td>
                    <td> <input type="time" name="endTime" value="${JobForm.endTime}" required> </td>
                    <td class="error"> <html:errors property="EndDateTimeError" /> </td>
                </tr>
                <tr>
                    <td>Pay Per Hour :</td>
                    <td> <html:text property="payPerHour" /> </td>
                    <td class="error"> <html:errors property="PayPerHourError" /> </td>
                </tr>

            </table>
            <html:submit value="Submit" />
        </html:form></div>
    </body>
</html>