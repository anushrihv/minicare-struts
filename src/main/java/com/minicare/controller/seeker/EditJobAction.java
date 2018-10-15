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
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditJobAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            JobForm jobForm = (JobForm) form ;
            Member member = (Member) req.getSession().getAttribute("CurrentUser");
            int jobId = Integer.parseInt(jobForm.getId());
            JobService jobService = JobService.getInstance();
            JobUtil jobUtil = JobUtil.getInstance();
            Job job = jobService.getJobByJobId(jobId);
            if(member.getMemberId()!= job.getPostedBy()){
                throw new MiniCareException("YOU ARE NOT AUTHORISED TO ACCESS THIS RESOURCE");
            }
            jobUtil.populateJobFormFromModel(job,jobForm);
            return mapping.findForward("editjob");
        }catch (Exception e){
            Logger logger = Logger.getLogger("EditJobAction");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }

//    private void action(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            Member memberModel = (Member) req.getSession().getAttribute("CurrentUser");
//            int jobId = Integer.parseInt(req.getParameter("JobId"));
//            JobService jobService = JobService.getInstance();
//            JobUtil jobUtil = JobUtil.getInstance();
//            Job jobModel = jobService.getJobByJobId(jobId);
//            if(memberModel.getMemberId()!=jobModel.getPostedBy()){
//                throw new MiniCareException("YOU ARE NOT AUTHORISED TO ACCESS THIS RESOURCE");
//            }
//            JobForm jobForm = jobUtil.populateJobFormFromModel(jobModel);
//            req.setAttribute("JobForm", jobForm);
//            getServletContext().getRequestDispatcher("/jsp/editJob.jsp").forward(req,resp);
//        }catch (Exception e){
//            Logger logger = Logger.getLogger("EditJobAction");
//            logger.log(Level.SEVERE,"exception occurred",e);
//            throw new MiniCareException(e);
//        }
//    }
}
