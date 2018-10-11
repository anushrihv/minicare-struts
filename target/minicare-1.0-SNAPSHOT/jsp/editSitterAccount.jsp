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
            <form action="/minicare-1.0-SNAPSHOT/jsp/sitter_homepage.jsp">
                <input type="submit" value="HOME PAGE">
            </form>
        </div>
        <div align="center">
        <form action="/minicare-1.0-SNAPSHOT/sitter/editaccount.do" method="post">

            <table>
                <tr>
                    <td>First Name :</td>
                    <td><input type="text" name="firstname" value="${SitterFormBean.firstname}"></td>
                    <td class="error"> ${FirstNameError} </td>
                </tr>
                <tr>
                    <td>Last Name :</td>
                    <td><input type="text" name="lastname" value="${SitterFormBean.lastname}"></td>
                    <td class="error"> ${LastNameError} </td>
                </tr>
                <tr>
                    <td>Phone Number : </td>
                    <td> <input type="text" name="phonenumber" value="${SitterFormBean.phonenumber}"></td>
                    <td class="error"> ${PhoneNumberError} </td>
                </tr>

                <tr>
                    <td> Address : </td>
                    <td> <textarea rows="4" cols="30" name="address" >${SitterFormBean.address.trim()}</textarea> </td>
                    <td class="error"> ${AddressError} </td>
                </tr>
                <tr>
                    <td> Years of Experience : </td>
                    <td> <input type="text" name="yearsofexperience" value="${SitterFormBean.yearsOfExperience}"> </td>
                    <td class="error"> ${YearsOfExperienceError} </td>
                </tr>
                <tr>
                    <td> Expected Pay : </td>
                    <td> <input type="text" name="expectedpay" value="${SitterFormBean.expectedPay}"></td>
                    <td class="error"> ${ExpectedPayError} </td>
                </tr>
                <input type="hidden" name="type" value="Sitter" > </td>
                <input type="hidden" name="memberId" value="${CurrentUser.memberId}">
                <input type="hidden" name="email" value="${CurrentUser.email}">
                <input type="hidden" name="password" value="${CurrentUser.password}">
                <input type="hidden" name="password2" value="${CurrentUser.password}">
            </table>
            <input type="submit" value="SAVE">
        </form></div>

    </body>
</html>