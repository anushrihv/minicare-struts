package com.minicare.service;

import com.minicare.controller.seeker.JobUtil;
import com.minicare.dao.JobApplicationDao;
import com.minicare.dao.JobDao;
import com.minicare.dto.JobForm;
import com.minicare.model.Job;
import com.minicare.model.Member;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

public class JobService {
    private static JobService jobService;

    private JobService(){

    }

    static{
        jobService = new JobService();
    }

    public static JobService getInstance(){
        return jobService;
    }

    public void storeJob(HttpServletRequest request,JobForm jobForm){
        JobUtil jobUtil = JobUtil.getInstance();
        Member member = (Member) request.getSession(false).getAttribute("CurrentUser");
        Job job = jobUtil.populateJobModel(jobForm);
        JobDao jobDao = JobDao.getInstance();
        jobDao.storeJob(job, member);
    }

    public List<Job> closeJob(int jobId, Member member)  {
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();
        JobDao jobDao = JobDao.getInstance();

        jobApplicationDao.closeJobApplicationByJobId(jobId);
        jobDao.closeJob(jobId);
        List<Job> jobList = jobDao.getJobsById(member);
        return jobList;
    }

    public Job getJobByJobId(int jobId) {
        JobDao jobDao = JobDao.getInstance();
        Job job = jobDao.getJobByJobId(jobId);
        return job;
    }

    public void updateJob(JobForm jobForm) {
        Job job = new Job();
        JobDao jobDao = JobDao.getInstance();

        job.setId(Integer.parseInt(jobForm.getId()));
        job.setJobTitle(jobForm.getJobTitle());
        job.setStartDateTime(Timestamp.valueOf(jobForm.getStartDateTime()));
        job.setEndDateTime(Timestamp.valueOf(jobForm.getEndDateTime()));
        job.setPayPerHour(Double.parseDouble(jobForm.getPayPerHour()));
        jobDao.updateJob(job);
    }

    public List<Job> getJobs(Member member){
        JobDao jobDao = JobDao.getInstance();
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();
        List<Job> jobList = jobDao.getJobs();
        Iterator<Job> iterator = jobList.iterator();
        while(iterator.hasNext()){
            Job job = iterator.next();
            int jobId = job.getId();
            if(jobApplicationDao.getJobApplication(jobId, member.getMemberId())!=null){
                iterator.remove();
            }
        }
        return jobList;
    }

    public void deleteJobsBySeeker(int seekerId){
        JobDao jobDao = JobDao.getInstance();
        jobDao.closeJobByMemberId(seekerId);
    }

    public List<Job> getJobsById(Member member){
        JobDao jobDao = JobDao.getInstance();

        return jobDao.getJobsById(member);
    }
}
