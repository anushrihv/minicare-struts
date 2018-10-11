package com.minicare.service;

import com.minicare.controller.sitter.SitterUtil;
import com.minicare.dao.MemberDao;
import com.minicare.dao.SitterDao;
import com.minicare.model.SitterModel;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
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

    public SitterModel getSitter(int sitterId) throws ClassNotFoundException,SQLException{
        SitterDao sitterDao = SitterDao.getInstance();
        return sitterDao.getSitter(sitterId);
    }

    public SitterModel editSitterAccount(HttpServletRequest request) throws ClassNotFoundException,SQLException, NamingException {
        SitterUtil sitterUtil = SitterUtil.getInstance();
        SitterDao sitterDao = SitterDao.getInstance();
        MemberDao memberDao = MemberDao.getInstance();

        SitterModel sitterModel = sitterUtil.populateSitterModelFromRequest(request);
        sitterDao.editSitter(sitterModel);
        memberDao.editMember(sitterModel);
        return sitterModel;
    }
}
