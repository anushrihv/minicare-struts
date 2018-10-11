package com.minicare.controller.sitter;

import com.minicare.exception.MiniCareException;
import com.minicare.dto.SitterForm;
import com.minicare.model.MemberModel;
import com.minicare.model.SitterModel;
import com.minicare.service.MemberService;
import com.minicare.service.SitterService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    private void action(HttpServletRequest req, HttpServletResponse resp) {
        try {
            MemberModel memberModel = (MemberModel) req.getSession().getAttribute("CurrentUser");
            SitterService sitterService = SitterService.getInstance();
            MemberService memberService = MemberService.getInstance();
            SitterUtil sitterUtil = SitterUtil.getInstance();
            SitterModel sitterModel = sitterService.getSitter(memberModel.getMemberId());

            req.setAttribute("SitterModel",sitterModel);
            SitterForm sitterForm = sitterUtil.populateSitterFormBean(req);
            //SitterForm sitterForm = (SitterForm) req.getAttribute("SitterForm");
//            if(!sitterForm.validate(req)){
//                getServletContext().getRequestDispatcher("/jsp/editSitterAccount.jsp").forward(req,resp);
//            }else{
                sitterModel = sitterService.editSitterAccount(req);
                HttpSession session = req.getSession();
                session.setAttribute("CurrentUser",sitterModel);
                req.setAttribute("HomePageMessage","Account Successfully edited");
                getServletContext().getRequestDispatcher("/jsp/sitter_homepage.jsp").forward(req,resp);
           // }
        }catch (Exception e){
            Logger logger = Logger.getLogger("EditAccountAction");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }
}
