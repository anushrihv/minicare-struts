package com.minicare.controller.sitter;

import com.minicare.exception.MiniCareException;
import com.minicare.dto.JobApplicationDTO;
import com.minicare.service.JobApplicationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListJobApplication extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    private void action(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            JobApplicationService jobApplicationService = JobApplicationService.getInstance();
            List<JobApplicationDTO> jobApplicationDTOList = jobApplicationService.getJobApplicationList(req);
            req.setAttribute("ActiveJobApplicationList",jobApplicationDTOList);
            getServletContext().getRequestDispatcher("/jsp/listActiveJobApplications.jsp").forward(req,resp);
        }catch (Exception e){
            Logger logger = Logger.getLogger("ListJobApplication");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }
}
