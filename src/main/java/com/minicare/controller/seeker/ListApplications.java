package com.minicare.controller.seeker;

import com.minicare.dto.JobForm;
import com.minicare.exception.MiniCareException;
import com.minicare.dto.JobApplicationForm;
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

public class ListApplications extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            JobForm jobForm = (JobForm) form ;
            int jobId = Integer.parseInt(jobForm.getId());
            JobApplicationService jobApplicationService = JobApplicationService.getInstance();
            List<JobApplicationForm> jobApplicationFormList = jobApplicationService.getJobApplicationsByJobId(jobId);
            req.setAttribute("JobApplicationList", jobApplicationFormList);
            return mapping.findForward("showjobapplications");
        }catch (Exception e){
            Logger logger = Logger.getLogger("ListApplications");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }

//    private void action(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            int jobId = Integer.parseInt(req.getParameter("JobId"));
//            JobApplicationService jobApplicationService = JobApplicationService.getInstance();
//            List<JobApplicationForm> jobApplicationDTOList = jobApplicationService.getJobApplicationsByJobId(jobId);
//            req.setAttribute("JobApplicationList",jobApplicationDTOList);
//            getServletContext().getRequestDispatcher("/jsp/seekerJobApplications.jsp").forward(req,resp);
//        }catch (Exception e){
//            Logger logger = Logger.getLogger("ListApplications");
//            logger.log(Level.SEVERE,"exception occurred",e);
//            throw new MiniCareException(e);
//        }
//    }
}
