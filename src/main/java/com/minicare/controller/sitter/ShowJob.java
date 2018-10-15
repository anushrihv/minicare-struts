package com.minicare.controller.sitter;

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

public class ShowJob extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JobService jobService = JobService.getInstance();
        try{
            Member member = (Member) req.getSession().getAttribute("CurrentUser");
            List<Job> jobList = jobService.getJobs(member);
            req.setAttribute("JobList", jobList);
            return mapping.findForward("showjobs");
            //getServletContext().getRequestDispatcher("/jsp/showJobs.jsp").forward(req, resp);
        }catch (Exception e){
            throw new MiniCareException(e.getMessage());
        }
    }

//    private void action(HttpServletRequest req, HttpServletResponse resp) {
//        JobService jobService = JobService.getInstance();
//        try{
//            Member memberModel = (Member) req.getSession().getAttribute("CurrentUser");
//            List<Job> jobModelList = jobService.getJobs(memberModel);
//            req.setAttribute("JobList",jobModelList);
//            getServletContext().getRequestDispatcher("/jsp/showJobs.jsp").forward(req, resp);
//        }catch (Exception e){
//            throw new MiniCareException(e.getMessage());
//        }
//    }
}
