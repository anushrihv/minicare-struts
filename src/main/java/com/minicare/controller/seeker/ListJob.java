package com.minicare.controller.seeker;

import com.minicare.exception.MiniCareException;
import com.minicare.model.Job;
import com.minicare.service.SeekerService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListJob extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req , HttpServletResponse resp) throws Exception {
        SeekerService seekerService = SeekerService.getInstance();
        try {
            List<Job> jobList = seekerService.getJobsById(req);
            req.setAttribute("JobList", jobList);
            return mapping.findForward("listjobs");
        }catch(Exception e){
            Logger logger = Logger.getLogger("ListJob");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }

//    private void action(HttpServletRequest req, HttpServletResponse resp) {
//        SeekerService seekerService = SeekerService.getInstance();
//        try {
//            List<Job> jobModelList = seekerService.getJobsById(req);
//            req.setAttribute("JobList",jobModelList);
//            getServletContext().getRequestDispatcher("/jsp/listJobs.jsp").forward(req, resp);
//        }catch(Exception e){
//            Logger logger = Logger.getLogger("ListJob");
//            logger.log(Level.SEVERE,"exception occurred",e);
//            throw new MiniCareException(e);
//        }
//    }
}
