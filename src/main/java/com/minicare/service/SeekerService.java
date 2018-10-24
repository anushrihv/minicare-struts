package com.minicare.service;

import com.minicare.controller.seeker.SeekerUtil;
import com.minicare.dao.JobDao;
import com.minicare.dao.MemberDao;
import com.minicare.dao.SeekerDao;
import com.minicare.dto.SeekerForm;
import com.minicare.model.Job;
import com.minicare.model.Member;
import com.minicare.model.Seeker;
import com.minicare.model.Status;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

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

    public List<Job> getJobsById(HttpServletRequest request) throws SQLException,ClassNotFoundException{
        HttpSession session = request.getSession(false);
        jobDao = JobDao.getInstance();
        Member member = (Member)session.getAttribute("CurrentUser");
        List<Job> jobList = jobDao.getJobsById(member);
        return jobList;
    }

    public void closeSeekerAccount(Member member) {
        MemberDao memberDao = MemberDao.getInstance();
        SeekerDao seekerDao = SeekerDao.getInstance();

        member.setStatus(Status.INACTIVE);
        //memberDao.deleteMember(member);
        seekerDao.deleteSeeker(member);
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
