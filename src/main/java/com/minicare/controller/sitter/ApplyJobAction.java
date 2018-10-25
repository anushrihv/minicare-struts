package com.minicare.controller.sitter;

import com.minicare.dto.ApplyJobForm;
import com.minicare.exception.MiniCareException;
import com.minicare.service.JobApplicationService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplyJobAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try{
            ApplyJobForm applyJobForm = (ApplyJobForm) form;
            int id = Integer.parseInt(applyJobForm.getId());
            double expectedPay = Double.parseDouble(applyJobForm.getExpectedPay());
            JobApplicationService jobApplicationService = JobApplicationService.getInstance();
            jobApplicationService.storeJobApplication(req, id, expectedPay);
            return mapping.findForward("listjobapplications");
    }catch (Exception e){
            Logger logger = Logger.getLogger("ApplyJobFormAction");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }

//    private void action(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
//        try {
//            int id = (Integer)req.getSession(false).getAttribute("JobId");
//            double expectedPay = Double.parseDouble(req.getParameter("expectedpay"));
//            JobApplicationService jobApplicationService = JobApplicationService.getInstance();
//            jobApplicationService.storeJobApplication(req,id,expectedPay);
//            resp.sendRedirect("/minicare-1.0-SNAPSHOT/sitter/listjobapplications.do");
//        }catch (NumberFormatException n){
//            req.setAttribute("ExpectedPayError","invalid input");
//            getServletContext().getRequestDispatcher("/jsp/applyJob.jsp").forward(req,resp);
//        }catch (Exception e){
//            Logger logger = Logger.getLogger("ApplyJobFormAction");
//            logger.log(Level.SEVERE,"exception occurred",e);
//            throw new MiniCareException(e);
//        }
//    }
}
