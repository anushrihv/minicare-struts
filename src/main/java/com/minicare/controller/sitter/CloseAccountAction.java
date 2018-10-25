package com.minicare.controller.sitter;

import com.minicare.exception.MiniCareException;
import com.minicare.model.Member;
import com.minicare.model.Sitter;
import com.minicare.service.JobApplicationService;
import com.minicare.service.SitterService;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CloseAccountAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req , HttpServletResponse resp) throws Exception {
        try {
            SitterService sitterService = SitterService.getInstance();
            Member member = (Member) req.getSession().getAttribute("CurrentUser");
            Sitter sitter = (Sitter) member ;
            //jobApplicationService.closeSitterJobApplications(member.getMemberId());
            sitterService.closeSitterAccount(sitter);
            req.getSession(false).invalidate();
            ActionMessages actionMessages = new ActionMessages();
            actionMessages.add("Message", new ActionMessage("message.account.close"));
            saveMessages(req , actionMessages);
            return mapping.findForward("WelcomePage");

        }catch (Exception e){
            Logger logger = Logger.getLogger("CloseAccountAction");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }

//    private void action(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            JobApplicationService jobApplicationService = JobApplicationService.getInstance();
//            SitterService sitterService = SitterService.getInstance();
//            Member memberModel = (Member) req.getSession().getAttribute("CurrentUser");
//
//            jobApplicationService.closeSitterJobApplications(memberModel.getMemberId());
//            sitterService.closeSitterAccount(memberModel.getMemberId());
//            req.setAttribute("Message","Account has been successfully deleted");
//            req.getSession(false).invalidate();
//            getServletContext().getRequestDispatcher("/jsp/welcome.jsp").forward(req,resp);
//
//        }catch (Exception e){
//            Logger logger = Logger.getLogger("CloseAccountAction");
//            logger.log(Level.SEVERE,"exception occurred",e);
//            throw new MiniCareException(e);
//        }
//    }
}
