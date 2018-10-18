package com.minicare.service;

import com.minicare.dao.JobApplicationDao;
import com.minicare.dto.JobApplicationForm;
import com.minicare.model.JobApplication;
import com.minicare.model.Member;

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

    public void storeJobApplication(HttpServletRequest request , int jobId , double expectedPay) throws SQLException, NamingException {
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();

        populateJobApplicationModel(request,jobId,expectedPay);
        JobApplication jobApplication = (JobApplication) request.getAttribute("JobApplication");
        jobApplicationDao.storeJobApplication(jobApplication);
//        List<JobApplicationForm> jobApplicationDTOList = getJobApplicationList(request);
//        return jobApplicationDTOList;
    }

    private void populateJobApplicationModel(HttpServletRequest request , int jobId , double expectedPay){
        Member member = (Member) request.getSession().getAttribute("CurrentUser");

        JobApplication jobApplication = new JobApplication();
        jobApplication.setJobId(jobId);
        jobApplication.setMemberId(member.getMemberId());
        jobApplication.setExpectedPay(expectedPay);

        request.setAttribute("JobApplication", jobApplication);
    }

    public List<JobApplicationForm> getJobApplicationList(HttpServletRequest request) throws SQLException,NamingException{
        Member member = (Member) request.getSession().getAttribute("CurrentUser");
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();
        List<JobApplicationForm> jobApplicationFormList = jobApplicationDao.getJobApplicationList(member.getMemberId());
        return jobApplicationFormList;
    }

    public void deleteJobApplication(int jobId, int memberId) throws SQLException,NamingException{
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();
        jobApplicationDao.deleteJobApplication(jobId,memberId);
    }

    public List<JobApplicationForm> getJobApplicationsByJobId(int jobId) throws NamingException,SQLException{
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();
        List<JobApplicationForm> jobApplicationFormList = jobApplicationDao.getJobApplicationsByJobId(jobId);
        return jobApplicationFormList;
    }

    public void closeSitterJobApplications(int sitterId) throws NamingException,SQLException{
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();
        jobApplicationDao.closeJobApplicationsByMemberId(sitterId);
    }

    public void deleteJobApplicationsByJobId(int memberId) throws  NamingException,SQLException{
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();
        jobApplicationDao.deleteJobApplicationByJobId(memberId);
    }
}
