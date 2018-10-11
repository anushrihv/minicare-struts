package com.minicare.controller.member;

import com.minicare.exception.MiniCareException;
import com.minicare.dto.PasswordHashHelper;
import com.minicare.model.MemberModel;
import com.minicare.model.Type;
import com.minicare.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResetPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    private void action(HttpServletRequest req, HttpServletResponse resp) {
        try{
            String url = String.valueOf(req.getRequestURL());
            if(url.contains("/resetpasswordform.do"))
                getServletContext().getRequestDispatcher("/jsp/resetPassword.jsp").forward(req,resp);
            else{
                MemberService memberService = MemberService.getInstance();
                MemberModel memberModel = (MemberModel) req.getSession(false).getAttribute("CurrentUser");
                if(!memberService.checkPassword(req)){
                    getServletContext().getRequestDispatcher("/jsp/resetPassword.jsp").forward(req,resp);
                }else{
                    memberService.updatePassword(memberModel.getMemberId(),req.getParameter("newpassword"));
                    String newPasswordHash = PasswordHashHelper.get_SHA_256_SecurePassword(req.getParameter("newpassword"));
                    memberModel.setPassword(newPasswordHash);
                    req.getSession().setAttribute("CurrentUser",memberModel);
                    req.setAttribute("HomePageMessage","Password successfully updated");
                    if(memberModel.getType().name().equals(Type.SITTER.name())){
                        getServletContext().getRequestDispatcher("/jsp/sitter_homepage.jsp").forward(req,resp);
                    }else{
                        getServletContext().getRequestDispatcher("/jsp/seeker_homepage.jsp").forward(req,resp);
                    }
                }
            }
        }catch (Exception e){
            Logger logger = Logger.getLogger("ResetPassword");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw  new MiniCareException(e);
        }
    }
}
