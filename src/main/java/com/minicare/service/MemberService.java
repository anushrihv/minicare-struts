package com.minicare.service;

import com.minicare.dao.MemberDao;
import com.minicare.dto.PasswordHashHelper;
import com.minicare.model.MemberModel;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MemberService {
    private static MemberService memberService;

    private MemberService(){

    }

    static {
        memberService = new MemberService();
    }

    public static MemberService getInstance(){
        return memberService;
    }

    public  boolean uniqueEmail(String email) throws ClassNotFoundException, SQLException {
        MemberDao memberDao = MemberDao.getInstance();
        Set<MemberModel> memberModelSet = memberDao.getMember(email);
        Iterator<MemberModel> iterator = memberModelSet.iterator();
        if(iterator.hasNext()){
            return false;
        }
        else
            return true;
    }

    public boolean checkPassword(HttpServletRequest request) {
        boolean status=true;
        MemberModel memberModel = (MemberModel) request.getSession().getAttribute("CurrentUser");
        String currentPasswordHash = PasswordHashHelper.get_SHA_256_SecurePassword(request.getParameter("oldpassword"));
        request.setAttribute("OldPassword",request.getParameter("oldpassword"));
        request.setAttribute("NewPassword",request.getParameter("newpassword"));
        request.setAttribute("NewPassword2",request.getParameter("newpassword2"));
        if(!memberModel.getPassword().equals(currentPasswordHash)){
            request.setAttribute("OldPasswordError","Incorrect password");
            status = false;
        }if(request.getParameter("oldpassword").equals(request.getParameter("newpassword"))){
            request.setAttribute("NewPasswordError","New password cannot be the same as old password");
            return false;
        }if(!request.getParameter("newpassword").equals(request.getParameter("newpassword2"))){
            request.setAttribute("NewPasswordError","Passwords don't match");
            status = false;
        }
        return status;
    }

    public void updatePassword(int memberId , String newPassword) throws NamingException, SQLException{
        MemberDao memberDao = MemberDao.getInstance();
        String newPasswordHash = PasswordHashHelper.get_SHA_256_SecurePassword(newPassword);
        memberDao.updatePassword(memberId,newPasswordHash);
    }

    public Set<MemberModel> searchMember(String email) throws NamingException, SQLException {
        MemberDao memberDao = MemberDao.getInstance();
//        Set<MemberModel> memberModelSet = memberDao.getAllMembers();
//        Set<MemberModel> searchResultSet = new HashSet<>();
//        Iterator<MemberModel> iterator = memberModelSet.iterator();
//        MemberModel memberModel;
//        while(iterator.hasNext()){
//            memberModel = iterator.next();
//            String dbemail = memberModel.getEmail();
//            if(dbemail.contains(email)){
//                searchResultSet.add(memberModel);
//            }
//        }
//        return searchResultSet;

        Set<MemberModel> memberModelSet = memberDao.searchMember(email);
        return memberModelSet;
    }
}
