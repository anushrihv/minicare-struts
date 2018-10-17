package com.minicare.service;

import com.minicare.controller.seeker.SeekerUtil;
import com.minicare.controller.sitter.SitterUtil;
import com.minicare.controller.visitor.VisitorUtil;
import com.minicare.dao.MemberDao;
import com.minicare.dao.SeekerDao;
import com.minicare.dao.SitterDao;
import com.minicare.dto.*;
import com.minicare.model.*;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class VisitorService{
    private static VisitorService visitorService;
    private SeekerForm seekerForm;
    private SitterForm sitterForm;
    private Sitter sitterModel;
    private Seeker seekerModel;
    private SeekerDao seekerDao;
    private SitterDao sitterDao;
    private MemberDao memberDao;
    private Member member;


    static {
        visitorService = new VisitorService();
    }

    private VisitorService(){

    }

    public static VisitorService getInstance(){
        return visitorService;
    }


    public void storeSitterDetails(SitterForm sitterForm,HttpSession session) throws SQLException,ClassNotFoundException{
        boolean isRegister = true;
        VisitorUtil visitorUtil = VisitorUtil.getInstance();
        SitterUtil sitterUtil = SitterUtil.getInstance();
        Sitter sitterModel = sitterUtil.populateSitterModel(sitterForm,isRegister);
        sitterDao = SitterDao.getInstance();
        sitterDao.insertSitter(sitterModel);
        visitorUtil.populateModelFromDb(sitterModel.getEmail(),session);
    }

    public void storeSeekerDetails(SeekerForm seekerForm,HttpSession session) throws SQLException,ClassNotFoundException {
        boolean isRegister = true ;
        VisitorUtil visitorUtil = VisitorUtil.getInstance();
        SeekerUtil seekerUtil = SeekerUtil.getInstance();

        Seeker seekerModel = seekerUtil.populateSeekerModel(seekerForm,isRegister);
        seekerDao = SeekerDao.getInstance();
        seekerDao.insertSeeker(seekerModel);
        visitorUtil.populateModelFromDb(seekerModel.getEmail(),session);
    }


    public boolean authenticate(HttpServletRequest req , ActionForm actionForm) throws SQLException,ClassNotFoundException{
        LoginForm loginForm = (LoginForm) actionForm;
        boolean status=true;
        memberDao = MemberDao.getInstance();
        List memberSet = memberDao.getMember(loginForm.getEmail());
        Iterator iterator = memberSet.iterator();
        if(!iterator.hasNext()){
            status=false;
        }else{
            Member member = (Member) iterator.next();
            String memberStatus = member.getStatus().name();
            if(memberStatus.equals("INACTIVE")){
                status=false;
            }
            String dbPassword = member.getPassword();
            String userPasswordHash = PasswordHashHelper.get_SHA_256_SecurePassword(req.getParameter("password"));
            if(!userPasswordHash.equals(dbPassword)){
                status=false;
            }
            loginForm.setType(member.getType().name());
        }

        return status;
    }




}
