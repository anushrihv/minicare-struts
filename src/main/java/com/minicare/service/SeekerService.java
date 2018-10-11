package com.minicare.service;

import com.minicare.controller.seeker.SeekerUtil;
import com.minicare.dao.JobDao;
import com.minicare.dao.MemberDao;
import com.minicare.dao.SeekerDao;
import com.minicare.dto.SeekerForm;
import com.minicare.model.JobModel;
import com.minicare.model.MemberModel;
import com.minicare.model.SeekerModel;
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

    public List<JobModel> getJobsById(HttpServletRequest request) throws SQLException,ClassNotFoundException{
        HttpSession session = request.getSession(false);
        jobDao = JobDao.getInstance();
        MemberModel memberModel = (MemberModel)session.getAttribute("CurrentUser");
        List<JobModel> jobModelList= jobDao.getJobsById(memberModel);
        return jobModelList;
    }

    public void closeSeekerAccount(int seekerId) throws NamingException,SQLException{
        MemberDao memberDao = MemberDao.getInstance();
        memberDao.deleteMember(seekerId);
    }

    public SeekerModel getSeeker(int seekerId) throws ClassNotFoundException,SQLException{
        SeekerDao seekerDao = SeekerDao.getInstance();
        return seekerDao.getSeeker(seekerId);
    }


    public SeekerModel editSeekerAccount(SeekerForm seekerForm) throws ClassNotFoundException,SQLException, NamingException {
        SeekerUtil seekerUtil = SeekerUtil.getInstance();
        SeekerDao seekerDao = SeekerDao.getInstance();
        MemberDao memberDao = MemberDao.getInstance();

        SeekerModel seekerModel = seekerUtil.populateSeekerModel(seekerForm);
        seekerDao.editSeeker(seekerModel);
        memberDao.editMember(seekerModel);
        return seekerModel;
    }
}
