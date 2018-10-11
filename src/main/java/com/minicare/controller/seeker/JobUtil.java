package com.minicare.controller.seeker;

import com.minicare.dto.JobFormBean;
import com.minicare.model.JobModel;
import com.minicare.model.MemberModel;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

public class JobUtil {
    public static JobUtil jobUtil;

    private JobUtil(){

    }

    static {
        jobUtil = new JobUtil();
    }

    public static JobUtil getInstance(){
        return jobUtil;
    }

    public JobFormBean populateJobFormBean(HttpServletRequest request)  {
        MemberModel memberModel = (MemberModel) request.getSession().getAttribute("CurrentUser");
        JobFormBean jobFormBean = new JobFormBean();
        jobFormBean.setId(request.getParameter("jobid"));
        jobFormBean.setJobTitle(request.getParameter("jobtitle"));
        jobFormBean.setStartDate(request.getParameter("startdate"));
        jobFormBean.setStartTime(request.getParameter("starttime"));
        jobFormBean.setEndDate(request.getParameter("enddate"));
        jobFormBean.setEndTime(request.getParameter("endtime"));
        jobFormBean.setStartDateTime();
        jobFormBean.setEndDateTime();
        jobFormBean.setPayPerHour(request.getParameter("payperhour"));
        jobFormBean.setPostedBy(String.valueOf(memberModel.getMemberId()));
        request.setAttribute("JobFormBean",jobFormBean);
        return jobFormBean;
    }

    public JobModel populateJobModelFromRequest(HttpServletRequest request){
        JobModel jobModel = new JobModel();
        JobFormBean jobFormBean = (JobFormBean) request.getAttribute("JobFormBean");
        Timestamp startDateTime = Timestamp.valueOf(jobFormBean.getStartDateTime());
        Timestamp endDateTime = Timestamp.valueOf(jobFormBean.getEndDateTime());
        double payPerHour = Double.parseDouble(jobFormBean.getPayPerHour());

        jobModel.setJobTitle(jobFormBean.getJobTitle());
        jobModel.setStartDateTime(startDateTime);
        jobModel.setEndDateTime(endDateTime);
        jobModel.setPayPerHour(payPerHour);

        request.setAttribute("JobModel",jobModel);
        return jobModel;
    }

    public JobFormBean populateJobFormFromModel(JobModel jobModel){
        JobFormBean jobFormBean = new JobFormBean();
        jobFormBean.setId(String.valueOf(jobModel.getId()));
        jobFormBean.setJobTitle(jobModel.getJobTitle());
        jobFormBean.setStartDate(jobModel.getStartDateTime().toString().split(" ")[0]);
        jobFormBean.setStartTime(jobModel.getStartDateTime().toString().split(" ")[1].substring(0,5));
        jobFormBean.setEndDate(jobModel.getEndDateTime().toString().split(" ")[0]);
        jobFormBean.setEndTime(jobModel.getEndDateTime().toString().split(" ")[1].substring(0,5));
        jobFormBean.setStartDateTime();
        jobFormBean.setEndDateTime();
        jobFormBean.setPayPerHour(String.valueOf(jobModel.getPayPerHour()));
        return jobFormBean;
    }
}
