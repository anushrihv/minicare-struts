package com.minicare.service;

import com.minicare.dao.JobApplicationDao;
import com.minicare.dao.JobDao;
import com.minicare.dao.SitterDao;
import com.minicare.dto.JobApplicationForm;
import com.minicare.model.*;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class JobApplicationService {
    static JobApplicationService jobApplicationService;

    static {
        jobApplicationService = new JobApplicationService();
    }

    private JobApplicationService(){

    }

    public static JobApplicationService getInstance(){
        return jobApplicationService;
    }

    public void storeJobApplication(HttpServletRequest request , int jobId , double expectedPay) {
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();

        populateJobApplicationModel(request,jobId,expectedPay);
        JobApplication jobApplication = (JobApplication) request.getAttribute("JobApplication");
        jobApplicationDao.storeJobApplication(jobApplication);
    }

    private void populateJobApplicationModel(HttpServletRequest request , int jobId , double expectedPay){
        Member member = (Member) request.getSession().getAttribute("CurrentUser");
        JobDao jobDao = JobDao.getInstance();
        SitterDao sitterDao = SitterDao.getInstance();

        JobApplication jobApplication = new JobApplication();
        jobApplication.setJob(jobDao.getJobByJobId(jobId));
        jobApplication.setSitter(sitterDao.getSitter(member.getMemberId()));
        jobApplication.setExpectedPay(expectedPay);

        request.setAttribute("JobApplication", jobApplication);
    }

    public List<JobApplication> getJobApplicationList(HttpServletRequest request) {
        Member member = (Member) request.getSession().getAttribute("CurrentUser");
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();
        List<JobApplication> jobApplicationList = jobApplicationDao.getJobApplicationList(member.getMemberId());
        return jobApplicationList;
    }

    public void deleteJobApplication(JobApplication jobApplication){
        jobApplication.setStatus(Status.INACTIVE);
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();
        jobApplicationDao.deleteJobApplication(jobApplication);
    }

    public List<JobApplicationForm> getJobApplicationsByJobId(int jobId) {
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();
        List<JobApplicationForm> jobApplicationFormList = jobApplicationDao.getJobApplicationsByJobId(jobId);
        return jobApplicationFormList;
    }

    public JobApplication getJobApplication(int jobId,int memberId){
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();
        return jobApplicationDao.getJobApplication(jobId,memberId);
    }

    public void deleteJobApplicationsByJobId(int memberId){
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();
        jobApplicationDao.deleteJobApplicationByJobId(memberId);
    }
}
