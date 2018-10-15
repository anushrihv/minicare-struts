<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

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
        align:right;
    }

    .error{
        color:red;
    }
</style>

<html>
    <body>
        <h1 style="text-align:center"> EDIT ACCOUNT </h1>
        <div align="right">
            <html:form action="/sitterhomepage">
                <html:submit value="HOME PAGE" />
            </html:form>
        </div>
        <div align="center">
        <html:form action="/sitter/editaccount" method="post">

            <table>
                <tr>
                    <td>First Name :</td>
                    <td> <html:text property="firstname" /> </td>
                    <td class="error"> <html:errors property="FirstNameError" /> </td>
                </tr>
                <tr>
                    <td>Last Name :</td>
                    <td> <html:text property="lastname" /> </td>
                    <td class="error"> <html:errors property="LastNameError" /> </td>
                </tr>
                <tr>
                    <td>Phone Number : </td>
                    <td> <html:text property="phonenumber" /> </td>
                    <td class="error"> <html:errors property="PhoneNumberError" />  </td>
                </tr>

                <tr>
                    <td> Address : </td>
                    <td> <html:textarea rows="4" cols="30" property="address" /> </td>
                    <td class="error"> <html:errors property="AddressError" />  </td>
                </tr>
                <tr>
                    <td> Years of Experience : </td>
                    <td> <html:text property="yearsOfExperience" /> </td>
                    <td class="error"> <html:errors property="YearsOfExperienceError" />  </td>
                </tr>
                <tr>
                    <td> Expected Pay : </td>
                    <td> <html:text property="expectedPay" /> </td>
                    <td class="error"> <html:errors property="ExpectedPayError" />  </td>
                </tr>
                <html:hidden name="SitterForm" property="type" />
                <html:hidden property="memberId" />
                <html:hidden property="email" />
                <html:hidden property="password" />
                <html:hidden property="editform" value="true" />

            </table>
            <html:submit value="SAVE" />
        </html:form>
        </div>

    </body>
</html>