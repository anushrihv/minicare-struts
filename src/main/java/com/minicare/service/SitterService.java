package com.minicare.service;

import com.minicare.controller.sitter.SitterUtil;
import com.minicare.dao.MemberDao;
import com.minicare.dao.SitterDao;
import com.minicare.dto.SitterForm;
import com.minicare.model.JobApplication;
import com.minicare.model.Member;
import com.minicare.model.Sitter;
import com.minicare.model.Status;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Set;


public class SitterService {
    static SitterService sitterService;

    static {
        sitterService = new SitterService();
    }

    private SitterService(){

    }

    public static SitterService getInstance(){
        return sitterService;
    }

    public void closeSitterAccount(Sitter sitter){
        SitterDao sitterDao = SitterDao.getInstance();
        sitter.setStatus(Status.INACTIVE);
        Set<JobApplication> jobApplicationSet = sitter.getJobapplication();
        for(JobApplication jobApplication : jobApplicationSet){
            jobApplication.setStatus(Status.INACTIVE);
        }
        sitterDao.deleteSitter(sitter);
    }

    public Sitter getSitter(int sitterId) {
        SitterDao sitterDao = SitterDao.getInstance();
        return sitterDao.getSitter(sitterId);
    }

    public Sitter editSitterAccount(SitterForm sitterForm) {
        SitterUtil sitterUtil = SitterUtil.getInstance();
        SitterDao sitterDao = SitterDao.getInstance();

        Sitter sitterModel = sitterUtil.populateSitterModel(sitterForm,false);
        sitterDao.editSitter(sitterModel);
        return sitterModel;
    }
}
