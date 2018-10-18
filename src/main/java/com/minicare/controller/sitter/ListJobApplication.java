package com.minicare.controller.sitter;

import com.minicare.dto.JobApplicationForm;
import com.minicare.exception.MiniCareException;
import com.minicare.service.JobApplicationService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListJobApplication extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            JobApplicationService jobApplicationService = JobApplicationService.getInstance();
            List<JobApplicationForm> jobApplicationFormList = jobApplicationService.getJobApplicationList(req);
            req.setAttribute("MyJobApplicationList", jobApplicationFormList);
            return mapping.findForward("listmyjobapplications");
        }catch (Exception e){
            Logger logger = Logger.getLogger("ListJobApplication");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }

//    private void action(HttpServletRequest req, HttpServletResponse resp)  {
//        try {
//            JobApplicationService jobApplicationService = JobApplicationService.getInstance();
//            List<JobApplicationForm> jobApplicationDTOList = jobApplicationService.getJobApplicationList(req);
//            req.setAttribute("ActiveJobApplicationList",jobApplicationDTOList);
//            getServletContext().getRequestDispatcher("/jsp/listMyJobApplications.jsp").forward(req,resp);
//        }catch (Exception e){
//            Logger logger = Logger.getLogger("ListJobApplication");
//            logger.log(Level.SEVERE,"exception occurred",e);
//            throw new MiniCareException(e);
//        }
//    }
}
