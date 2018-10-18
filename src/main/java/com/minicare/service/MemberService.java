package com.minicare.service;

import com.minicare.dao.MemberDao;
import com.minicare.dto.PasswordHashHelper;
import com.minicare.model.Member;
import com.minicare.model.Type;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
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

    public  boolean uniqueEmail(String email)  {
        MemberDao memberDao = MemberDao.getInstance();
        List memberList = memberDao.getMember(email);
        Iterator iterator = memberList.iterator();
        if(iterator.hasNext()){
            return false;
        }
        else
            return true;
    }

    public ActionErrors checkPassword(HttpServletRequest request) {
        ActionErrors actionErrors = new ActionErrors();
        Member member = (Member) request.getSession().getAttribute("CurrentUser");
        String currentPasswordHash = PasswordHashHelper.get_SHA_256_SecurePassword(request.getParameter("oldpassword"));
        request.setAttribute("OldPassword",request.getParameter("oldpassword"));
        request.setAttribute("NewPassword",request.getParameter("newpassword"));
        request.setAttribute("NewPassword2",request.getParameter("newpassword2"));
        if(!member.getPassword().equals(currentPasswordHash)){
            actionErrors.add("OldPasswordError",new ActionMessage("error.password.incorrect"));
            //status = false;
        }if(request.getParameter("oldpassword").equals(request.getParameter("newpassword"))){
            actionErrors.add("NewPasswordError",new ActionMessage("error.password.different"));
            return actionErrors;
            //return false;
        }if(!request.getParameter("newpassword").equals(request.getParameter("newpassword2"))){
            actionErrors.add("NewPassword2Error",new ActionMessage("error.password.match"));
            //status = false;
        }
        return actionErrors;
    }

    public void updatePassword(int memberId , String newPassword){
        MemberDao memberDao = MemberDao.getInstance();
        String newPasswordHash = PasswordHashHelper.get_SHA_256_SecurePassword(newPassword);
        memberDao.updatePassword(memberId,newPasswordHash);
    }

    public List<Member> searchMember(String email, Type type){
        MemberDao memberDao = MemberDao.getInstance();

        List<Member> memberList = memberDao.searchMember(email,type);
        return memberList;
    }
}
