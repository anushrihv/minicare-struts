<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

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

        .error{
        color:red;
        }

</style>

<html>
    <body>
    <h1 style="text-align:center">Register here</h1>
        <div align="center">
        <html:form action="/sitterregister" method="POST">
            <table>
                <tr>
                    <td>First Name :</td>
                    <td><html:text property="firstname" /></td>
                    <td class="error"> <html:errors property="FirstNameError" /> </td>
                </tr>
                <tr>
                    <td>Last Name :</td>
                    <td><html:text property="lastname" /></td>
                    <td class="error"> <html:errors property="LastNameError" /> </td>
                </tr>
                <tr>
                    <td>Phone Number : </td>
                    <td> <html:text property="phonenumber" /></td>
                    <td class="error"> <html:errors property="PhoneNumberError" /> </td>
                </tr>
                <tr>
                    <td> Email : </td>
                    <td> <html:text property="email" /> </td>
                    <td class="error"> <html:errors property="EmailError" /> </td>
                </tr>
                <tr>
                    <td> Address : </td>
                    <td> <html:textarea rows="4" cols="30" property="address" /> </td>
                    <td class="error"> <html:errors property="AddressError" /> </td>
                </tr>
                <tr>
                    <td>Password : </td>
                    <td> <html:password property="password" /> </td>
                    <td class="error"> <html:errors property="PasswordError" /> </td>
                </tr>
                <tr>
                    <td> Re enter password </td>
                    <td> <html:password property="password2" /> </td>
                    <td class="error"> <html:errors property="Password2Error" /> </td>
                </tr>
                <tr>
                    <td> Years Of Experience : </td>
                    <td> <html:text property="yearsOfExperience" /> </td>
                    <td class="error"> <html:errors property="YearsOfExperienceError" /> </td>
                </tr>
                <tr>
                    <td> Expected Pay : </td>
                    <td> <html:text property="expectedPay" /> </td>
                    <td class="error"> <html:errors property="ExpectedPayError" /> </td>
                </tr>
                <html:hidden property="editform" value="false" />
            </table>
            <html:submit value="Submit" />
        </html:form>
        </div>

        <div align="center">
        <html:form action="/welcomepage" >
            <html:submit value="BACK" />
        </html:form>
        </div>
    </body>
</html>