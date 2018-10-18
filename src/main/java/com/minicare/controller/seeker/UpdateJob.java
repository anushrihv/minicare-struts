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

public class UpdateJob extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            JobForm jobForm = (JobForm) form ;
            int jobId = Integer.parseInt(jobForm.getId());
            JobService jobService = JobService.getInstance();
            Job job = jobService.getJobByJobId(jobId);
            Member member = (Member) req.getSession(false).getAttribute("CurrentUser");
            if(member.getMemberId()!= job.getPostedBy()){
                throw new MiniCareException("YOU ARE NOT AUTHORISED TO ACCESS THIS RESOURCE");
            }
            req.setAttribute("Job", job);
            jobService.updateJob(jobForm);
            List<Job> jobList = jobService.getJobsById(member);
            req.setAttribute("JobList", jobList);
            return mapping.findForward("listjobs");
            //}
        }catch (Exception e){
            Logger logger = Logger.getLogger("UpdateJob");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }

//    private void action(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            int jobId = Integer.parseInt(req.getParameter("jobid"));
//            JobService jobService = JobService.getInstance();
//            JobUtil jobUtil = JobUtil.getInstance();
//            Job jobModel = jobService.getJobByJobId(jobId);
//            Member memberModel = (Member) req.getSession(false).getAttribute("CurrentUser");
//            if(memberModel.getMemberId()!=jobModel.getPostedBy()){
//                throw new MiniCareException("YOU ARE NOT AUTHORISED TO ACCESS THIS RESOURCE");
//            }
//            req.setAttribute("Job",jobModel);
//            JobForm jobForm = jobUtil.populateJobFormBean(req);
//            //JobForm jobForm = (JobForm) req.getAttribute("JobForm");
////            if(!jobForm.validate(req)){
////                getServletContext().getRequestDispatcher("/jsp/editJob.jsp").forward(req,resp);
////            }else {
//                jobService.updateJob(jobForm);
//                List<Job> jobModelList = jobService.getJobsById(memberModel);
//                req.setAttribute("JobList", jobModelList);
//                getServletContext().getRequestDispatcher("/jsp/listJobs.jsp").forward(req, resp);
//            //}
//        }catch (Exception e){
//            Logger logger = Logger.getLogger("UpdateJob");
//            logger.log(Level.SEVERE,"exception occurred",e);
//            throw new MiniCareException(e);
//        }
//    }
}
