package com.minicare.service;

import com.minicare.controller.sitter.SitterUtil;
import com.minicare.dao.MemberDao;
import com.minicare.dao.SitterDao;
import com.minicare.dto.SitterForm;
import com.minicare.model.Member;
import com.minicare.model.Sitter;
import com.minicare.model.Status;

import javax.naming.NamingException;
import java.sql.SQLException;


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

    public void closeSitterAccount(Member member){
        SitterDao sitterDao = SitterDao.getInstance();
        MemberDao memberDao = MemberDao.getInstance();

        member.setStatus(Status.INACTIVE);
        sitterDao.deleteSitter(member);
        //memberDao.deleteMember(member);
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
