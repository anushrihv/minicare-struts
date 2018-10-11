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

public class PostJob extends HttpServlet {
    private JobFormBean jobFormBean;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    private void action(HttpServletRequest req , HttpServletResponse resp){
        try {

            String url = String.valueOf(req.getRequestURL());
            if(url.contains("/seeker/postjobform.do")){
                getServletContext().getRequestDispatcher("/jsp/postJob.jsp").forward(req,resp);

            }else if(url.contains("/seeker/postjob.do")) {
                MemberModel memberModel = (MemberModel) req.getSession().getAttribute("CurrentUser");
                JobUtil jobUtil = JobUtil.getInstance();
                JobService jobService = JobService.getInstance();
                JobFormBean jobFormBean = jobUtil.populateJobFormBean(req);
                //jobFormBean = (JobFormBean) req.getAttribute("JobFormBean");
                if (jobFormBean!=null && !jobFormBean.validate(req)) {
                    getServletContext().getRequestDispatcher("/jsp/postJob.jsp").forward(req, resp);
                }else{
                    jobService.storeJob(req);
                    List<JobModel> jobModelList = jobService.getJobsById(memberModel);
                    req.setAttribute("JobList",jobModelList);
                    getServletContext().getRequestDispatcher("/jsp/listJobs.jsp").forward(req,resp);
                }
            }
        }catch (Exception e){
            Logger logger = Logger.getLogger("PostJob");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }
}
