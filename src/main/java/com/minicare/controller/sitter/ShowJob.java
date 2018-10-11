package com.minicare.controller.sitter;

import com.minicare.exception.MiniCareException;
import com.minicare.model.JobModel;
import com.minicare.model.MemberModel;
import com.minicare.service.JobService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowJob extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    private void action(HttpServletRequest req, HttpServletResponse resp) {
        JobService jobService = JobService.getInstance();
        try{
            MemberModel memberModel = (MemberModel) req.getSession().getAttribute("CurrentUser");
            List<JobModel> jobModelList = jobService.getJobs(memberModel);
            req.setAttribute("JobList",jobModelList);
            getServletContext().getRequestDispatcher("/jsp/showJobs.jsp").forward(req, resp);
        }catch (Exception e){
            throw new MiniCareException(e.getMessage());
        }
    }
}
