package com.minicare.controller.seeker;

import com.minicare.exception.MiniCareException;
import com.minicare.model.Member;
import com.minicare.service.JobApplicationService;
import com.minicare.service.JobService;
import com.minicare.service.SeekerService;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CloseAccountAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            JobApplicationService jobApplicationService = JobApplicationService.getInstance();
            JobService jobService = JobService.getInstance();
            SeekerService seekerService = SeekerService.getInstance();
            Member member = (Member) req.getSession().getAttribute("CurrentUser");

            jobService.deleteJobsBySeeker(member.getMemberId());
            jobApplicationService.deleteJobApplicationsByJobId(member.getMemberId());
            seekerService.closeSeekerAccount(member);
            req.getSession().invalidate();
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
//            JobService jobService = JobService.getInstance();
//            SeekerService seekerService = SeekerService.getInstance();
//            Member memberModel = (Member) req.getSession().getAttribute("CurrentUser");
//
//            jobService.deleteJobsBySeeker(memberModel.getMemberId());
//            jobApplicationService.deleteJobApplicationsByJobId(memberModel.getMemberId());
//            seekerService.closeSeekerAccount(memberModel.getMemberId());
//            req.setAttribute("Message","Account has been successfully deleted");
//            req.getSession().invalidate();
//            getServletContext().getRequestDispatcher("/jsp/welcome.jsp").forward(req,resp);
//        }catch (Exception e){
//            Logger logger = Logger.getLogger("CloseAccountAction");
//            logger.log(Level.SEVERE,"exception occurred",e);
//            throw new MiniCareException(e);
//        }
//
//    }
}
