package com.minicare.controller.seeker;

import com.minicare.exception.MiniCareException;
import com.minicare.dto.JobFormBean;
import com.minicare.model.JobModel;
import com.minicare.model.MemberModel;
import com.minicare.service.JobService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateJob extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    private void action(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int jobId = Integer.parseInt(req.getParameter("jobid"));
            JobService jobService = JobService.getInstance();
            JobUtil jobUtil = JobUtil.getInstance();
            JobModel jobModel = jobService.getJobByJobId(jobId);
            MemberModel memberModel = (MemberModel) req.getSession(false).getAttribute("CurrentUser");
            if(memberModel.getMemberId()!=jobModel.getPostedBy()){
                throw new MiniCareException("YOU ARE NOT AUTHORISED TO ACCESS THIS RESOURCE");
            }
            req.setAttribute("JobModel",jobModel);
            JobFormBean jobFormBean = jobUtil.populateJobFormBean(req);
            //JobFormBean jobFormBean = (JobFormBean) req.getAttribute("JobFormBean");
            if(!jobFormBean.validate(req)){
                getServletContext().getRequestDispatcher("/jsp/editJob.jsp").forward(req,resp);
            }else {
                jobService.updateJob(jobFormBean);
                List<JobModel> jobModelList = jobService.getJobsById(memberModel);
                req.setAttribute("JobList", jobModelList);
                getServletContext().getRequestDispatcher("/jsp/listJobs.jsp").forward(req, resp);
            }
        }catch (Exception e){
            Logger logger = Logger.getLogger("UpdateJob");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }
}
