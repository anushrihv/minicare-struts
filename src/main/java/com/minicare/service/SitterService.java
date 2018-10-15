package com.minicare.service;

import com.minicare.controller.sitter.SitterUtil;
import com.minicare.dao.MemberDao;
import com.minicare.dao.SitterDao;
import com.minicare.dto.SitterForm;
import com.minicare.model.Sitter;

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

    public void closeSitterAccount(int memberId) throws ClassNotFoundException,SQLException,NamingException{
        SitterDao sitterDao = SitterDao.getInstance();
        MemberDao memberDao = MemberDao.getInstance();

        sitterDao.deleteSitter(memberId);
        memberDao.deleteMember(memberId);
    }

    public Sitter getSitter(int sitterId) throws ClassNotFoundException,SQLException{
        SitterDao sitterDao = SitterDao.getInstance();
        return sitterDao.getSitter(sitterId);
    }

    public Sitter editSitterAccount(SitterForm sitterForm) throws ClassNotFoundException,SQLException, NamingException {
        boolean isRegister = false;
        SitterUtil sitterUtil = SitterUtil.getInstance();
        SitterDao sitterDao = SitterDao.getInstance();
        MemberDao memberDao = MemberDao.getInstance();

        Sitter sitterModel = sitterUtil.populateSitterModel(sitterForm,isRegister);
        sitterDao.editSitter(sitterModel);
        memberDao.editMember(sitterModel);
        return sitterModel;
    }
}
