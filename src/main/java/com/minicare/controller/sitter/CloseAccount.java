package com.minicare.controller.sitter;

import com.minicare.exception.MiniCareException;
import com.minicare.model.MemberModel;
import com.minicare.service.JobApplicationService;
import com.minicare.service.SitterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CloseAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    private void action(HttpServletRequest req, HttpServletResponse resp) {
        try {
            JobApplicationService jobApplicationService = JobApplicationService.getInstance();
            SitterService sitterService = SitterService.getInstance();
            MemberModel memberModel = (MemberModel) req.getSession().getAttribute("CurrentUser");

            jobApplicationService.closeSitterJobApplications(memberModel.getMemberId());
            sitterService.closeSitterAccount(memberModel.getMemberId());
            req.setAttribute("Message","Account has been successfully deleted");
            req.getSession(false).invalidate();
            getServletContext().getRequestDispatcher("/jsp/welcome.jsp").forward(req,resp);

        }catch (Exception e){
            Logger logger = Logger.getLogger("CloseAccount");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }
}
