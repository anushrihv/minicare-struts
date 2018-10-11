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
        <h1>EDIT JOB</h1>
        <div align="right"><form action="/minicare-1.0-SNAPSHOT/jsp/seeker_homepage.jsp" class="button"> <input type="submit" value="HOME" > </form></div>
        <div align ="center"><form action="/minicare-1.0-SNAPSHOT/seeker/updatejob.do" method="post">
            <table>
                <tr>
                    <td> <input type="hidden" name="jobid" value="${JobFormBean.id}"> </td>
                </tr>
                <tr>
                    <td>Job Title : </td>
                    <td> <input type="text" name="jobtitle" value="${JobFormBean.jobTitle}" required> </td>
                    <td class="error"> ${JobTitleError} </td>
                </tr>
                <tr>
                    <td>Start Date Time :</td>
                    <td> <input type="date" name="startdate" value="${JobFormBean.startDate}" required> </td>
                    <td> <input type="time" name="starttime" value="${JobFormBean.startTime}" required> </td>
                    <td class="error"> ${StartDateTimeError} </td>
                </tr>
                <tr>
                    <td>End Date Time :</td>
                    <td> <input type="date" name="enddate" value="${JobFormBean.endDate}" required> </td>
                    <td> <input type="time" name="endtime" value="${JobFormBean.endTime}" required> </td>
                    <td class="error"> ${EndDateTimeError} </td>
                </tr>
                <tr>
                    <td>Pay Per Hour :</td>
                    <td> <input type="text" name="payperhour" value="${JobFormBean.payPerHour}" required> </td>
                    <td class="error"> ${PayPerHourError} </td>
                </tr>

            </table>
            <input type="submit" value="SAVE">
        </form></div>
    </body>
</html>