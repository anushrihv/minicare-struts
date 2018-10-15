package com.minicare.controller.seeker;

import com.minicare.dto.JobForm;
import com.minicare.model.Job;
import com.minicare.model.Member;

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

    public JobForm populateJobFormBean(HttpServletRequest request)  {
        Member member = (Member) request.getSession().getAttribute("CurrentUser");
        JobForm jobForm = new JobForm();
        jobForm.setId(request.getParameter("jobid"));
        jobForm.setJobTitle(request.getParameter("jobtitle"));
        jobForm.setStartDate(request.getParameter("startdate"));
        jobForm.setStartTime(request.getParameter("starttime"));
        jobForm.setEndDate(request.getParameter("enddate"));
        jobForm.setEndTime(request.getParameter("endtime"));
        jobForm.setStartDateTime();
        jobForm.setEndDateTime();
        jobForm.setPayPerHour(request.getParameter("payperhour"));
        jobForm.setPostedBy(String.valueOf(member.getMemberId()));
        request.setAttribute("JobForm", jobForm);
        return jobForm;
    }

    public Job populateJobModel(JobForm jobForm){
        Job job = new Job();
        Timestamp startDateTime = Timestamp.valueOf(jobForm.getStartDateTime());
        Timestamp endDateTime = Timestamp.valueOf(jobForm.getEndDateTime());
        double payPerHour = Double.parseDouble(jobForm.getPayPerHour());

        job.setJobTitle(jobForm.getJobTitle());
        job.setStartDateTime(startDateTime);
        job.setEndDateTime(endDateTime);
        job.setPayPerHour(payPerHour);

        return job;
    }

    public void populateJobFormFromModel(Job job, JobForm jobForm){

        jobForm.setId(String.valueOf(job.getId()));
        jobForm.setJobTitle(job.getJobTitle());
        jobForm.setStartDate(job.getStartDateTime().toString().split(" ")[0]);
        jobForm.setStartTime(job.getStartDateTime().toString().split(" ")[1].substring(0,5));
        jobForm.setEndDate(job.getEndDateTime().toString().split(" ")[0]);
        jobForm.setEndTime(job.getEndDateTime().toString().split(" ")[1].substring(0,5));
        jobForm.setStartDateTime();
        jobForm.setEndDateTime();
        jobForm.setPayPerHour(String.valueOf(job.getPayPerHour()));

    }
}
