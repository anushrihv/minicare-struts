package com.minicare.service;

import com.minicare.controller.seeker.SeekerUtil;
import com.minicare.controller.sitter.SitterUtil;
import com.minicare.controller.visitor.VisitorUtil;
import com.minicare.dao.MemberDao;
import com.minicare.dao.SeekerDao;
import com.minicare.dao.SitterDao;
import com.minicare.dto.*;
import com.minicare.model.*;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

public class VisitorService{
    private static VisitorService visitorService;
    private SeekerForm seekerForm;
    private SitterForm sitterForm;
    private SitterModel sitterModel;
    private SeekerModel seekerModel;
    private SeekerDao seekerDao;
    private SitterDao sitterDao;
    private MemberDao memberDao;
    private MemberModel memberModel;


    static {
        visitorService = new VisitorService();
    }

    private VisitorService(){

    }

    public static VisitorService getInstance(){
        return visitorService;
    }


    public void storeSitterDetails(SitterForm sitterForm,HttpSession session) throws SQLException,ClassNotFoundException{
        VisitorUtil visitorUtil = VisitorUtil.getInstance();
        SitterUtil sitterUtil = SitterUtil.getInstance();
        SitterModel sitterModel = sitterUtil.populateSitterModel(sitterForm);
        sitterDao = SitterDao.getInstance();
        sitterDao.insertSitter(sitterModel);
        visitorUtil.populateModelFromDb(sitterModel.getEmail(),session);
    }

    public void storeSeekerDetails(SeekerForm seekerForm,HttpSession session) throws SQLException,ClassNotFoundException {
        VisitorUtil visitorUtil = VisitorUtil.getInstance();
        SeekerUtil seekerUtil = SeekerUtil.getInstance();
        SeekerModel seekerModel = seekerUtil.populateSeekerModel(seekerForm);
        seekerDao = SeekerDao.getInstance();
        seekerDao.insertSeeker(seekerModel);
        visitorUtil.populateModelFromDb(seekerModel.getEmail(),session);
    }


    public boolean authenticate(HttpServletRequest req , ActionForm actionForm) throws SQLException,ClassNotFoundException{
        LoginForm loginForm = (LoginForm) actionForm;
        boolean status=true;
        memberDao = MemberDao.getInstance();
        Set<MemberModel> memberModelSet = memberDao.getMember(loginForm.getEmail());
        Iterator<MemberModel> iterator = memberModelSet.iterator();
        if(!iterator.hasNext()){
            status=false;
        }else{
            MemberModel memberModel = iterator.next();
            String memberStatus = memberModel.getStatus().name();
            if(memberStatus.equals("INACTIVE")){
                status=false;
            }
            String dbPassword = memberModel.getPassword();
            String userPasswordHash = PasswordHashHelper.get_SHA_256_SecurePassword(req.getParameter("password"));
            if(!userPasswordHash.equals(dbPassword)){
                status=false;
            }
            loginForm.setType(memberModel.getType().name());
        }

        return status;
    }




}
