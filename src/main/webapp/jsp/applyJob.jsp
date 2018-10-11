<html>
    <body>
        <h1 text-align="center"> Enter expected pay </h1>
        <div align="center"><form action="/minicare-1.0-SNAPSHOT/sitter/jobapplication.do">

            <input type="text" name="expectedpay" value="${JobApplicationModel.expectedPay}" required><p style="color:red"> ${ExpectedPayError} </p>
            <input type="hidden" name="memberId" value="${CurrentUser.memberId}">
            <input type="submit" value="Submit">
        </form></div>
    </body>
</html>