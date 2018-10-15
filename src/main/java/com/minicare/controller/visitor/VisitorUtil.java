package com.minicare.controller.visitor;

import com.minicare.dao.MemberDao;
import com.minicare.model.Member;

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

        Set<Member> memberSet = memberDao.getMember(email);
        Iterator<Member> iterator = memberSet.iterator();
        Member member = iterator.next();

        session.setAttribute("CurrentUser", member);
    }
}
