package com.minicare.controller.visitor;

import com.minicare.dao.MemberDao;
import com.minicare.dto.LoginForm;
import com.minicare.model.MemberModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

public class VisitorUtil {
    public static VisitorUtil visitorUtil;

    private VisitorUtil(){

    }

    static {
        visitorUtil = new VisitorUtil();
    }

    public static VisitorUtil getInstance(){
        return visitorUtil;
    }


    public void populateModelFromDb(String email, HttpSession session) throws SQLException,ClassNotFoundException{
        MemberDao memberDao = MemberDao.getInstance();

        Set<MemberModel> memberModelSet = memberDao.getMember(email);
        Iterator<MemberModel> iterator = memberModelSet.iterator();
        MemberModel memberModel = iterator.next();

        session.setAttribute("CurrentUser",memberModel);
    }
}
