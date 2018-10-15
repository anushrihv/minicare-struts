package com.minicare.controller.member;

import com.minicare.exception.MiniCareException;
import org.apache.struts.action.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Logout extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            HttpSession session = req.getSession(false);
            session.invalidate();
            ActionMessages actionMessages = new ActionMessages();
            actionMessages.add("Message",new ActionMessage("message.logout.success"));
            saveMessages(req,actionMessages);
            return mapping.findForward("/welcomepage");
        }catch (Exception e){
            Logger logger = Logger.getLogger("Logout");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }

//    private void action(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            HttpSession session = req.getSession(false);
//            session.invalidate();
//            req.setAttribute("Message", "You have been successfully logged out");
//            getServletContext().getRequestDispatcher("/jsp/welcome.jsp").forward(req, resp);
//        }catch (Exception e){
//            Logger logger = Logger.getLogger("Logout");
//            logger.log(Level.SEVERE,"exception occurred",e);
//            throw new MiniCareException(e);
//        }
//    }
}
