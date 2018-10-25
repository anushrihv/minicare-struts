package com.minicare.service;

import com.minicare.controller.seeker.SeekerUtil;
import com.minicare.dao.JobDao;
import com.minicare.dao.MemberDao;
import com.minicare.dao.SeekerDao;
import com.minicare.dto.SeekerForm;
import com.minicare.model.*;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class SeekerService {
    static SeekerService seekerService;
    private JobDao jobDao;

    static{
        seekerService = new SeekerService();
    }

    private SeekerService(){

    }

    public static SeekerService getInstance(){
        return seekerService;
    }

    public List<Job> getJobsById(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        jobDao = JobDao.getInstance();
        Member member = (Member)session.getAttribute("CurrentUser");
        List<Job> jobList = jobDao.getJobsById(member);
        return jobList;
    }

    public void closeSeekerAccount(Seeker seeker) {
        SeekerDao seekerDao = SeekerDao.getInstance();

        seeker.setStatus(Status.INACTIVE);
        Set<Job> jobSet = seeker.getJob();
        for(Job job : jobSet){
            job.setStatus(Status.INACTIVE);
            Set<JobApplication> jobApplicationSet = job.getJobapplication();
            for(JobApplication jobApplication : jobApplicationSet){
                jobApplication.setStatus(Status.INACTIVE);
            }
        }
        seekerDao.deleteSeeker(seeker);
    }

    public Seeker getSeeker(int seekerId){
        SeekerDao seekerDao = SeekerDao.getInstance();
        return seekerDao.getSeeker(seekerId);
    }


    public Seeker editSeekerAccount(SeekerForm seekerForm) {
        SeekerUtil seekerUtil = SeekerUtil.getInstance();
        SeekerDao seekerDao = SeekerDao.getInstance();

        Seeker seekerModel = seekerUtil.populateSeekerModel(seekerForm,false);
        seekerDao.editSeeker(seekerModel);
        return seekerModel;
    }
}
