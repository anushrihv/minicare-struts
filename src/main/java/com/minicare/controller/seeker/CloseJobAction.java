package com.minicare.controller.seeker;

import com.minicare.dto.JobForm;
import com.minicare.exception.MiniCareException;
import com.minicare.model.Job;
import com.minicare.model.Member;
import com.minicare.service.JobService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CloseJobAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            JobForm jobForm = (JobForm) form ;
            int jobId = Integer.parseInt(((JobForm) form).getId());
            JobService jobService = JobService.getInstance();
            Member member = (Member) req.getSession().getAttribute("CurrentUser");
            List<Job> jobList = jobService.closeJob(jobId, member);
            req.setAttribute("JobList", jobList);
            return mapping.findForward("listjobs");
        }catch (Exception e){
            Logger logger = Logger.getLogger("CloseJobAction");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }

//    private void action(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            int jobId = Integer.parseInt(req.getParameter("JobId"));
//            JobService jobService = JobService.getInstance();
//            Member memberModel = (Member) req.getSession().getAttribute("CurrentUser");
//            List<Job> jobModelList = jobService.closeJob(jobId,memberModel);
//            req.setAttribute("JobList",jobModelList);
//            getServletContext().getRequestDispatcher("/jsp/listJobs.jsp").forward(req,resp);
//        }catch (Exception e){
//            Logger logger = Logger.getLogger("CloseJobAction");
//            logger.log(Level.SEVERE,"exception occurred",e);
//            throw new MiniCareException(e);
//        }
//    }
}
