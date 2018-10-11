package com.minicare.controller.sitter;

import com.minicare.exception.MiniCareException;
import com.minicare.model.MemberModel;
import com.minicare.service.SitterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditAccountForm extends HttpServlet {
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
            MemberModel memberModel = (MemberModel) req.getSession().getAttribute("CurrentUser");
            SitterUtil sitterUtil = SitterUtil.getInstance();
            sitterUtil.populateSitterFormBeanBySitterModel(memberModel, req);
            getServletContext().getRequestDispatcher("/jsp/editSitterAccount.jsp").forward(req,resp);
        }catch (Exception e){
            Logger logger = Logger.getLogger("EditAccountFormAction");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }
}
