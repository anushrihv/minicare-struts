package com.minicare.controller.seeker;

import com.minicare.exception.MiniCareException;
import com.minicare.model.JobModel;
import com.minicare.service.SeekerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListJob extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    private void action(HttpServletRequest req, HttpServletResponse resp) {
        SeekerService seekerService = SeekerService.getInstance();
        try {
            List<JobModel> jobModelList = seekerService.getJobsById(req);
            req.setAttribute("JobList",jobModelList);
            getServletContext().getRequestDispatcher("/jsp/listJobs.jsp").forward(req, resp);
        }catch(Exception e){
            Logger logger = Logger.getLogger("ListJob");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }
}
