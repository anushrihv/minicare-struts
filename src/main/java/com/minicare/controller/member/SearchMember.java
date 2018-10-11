package com.minicare.controller.member;

import com.minicare.exception.MiniCareException;
import com.minicare.model.MemberModel;
import com.minicare.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchMember extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    private void action(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String url = String.valueOf(req.getRequestURL());
            if (url.contains("searchform")) {
                getServletContext().getRequestDispatcher("/jsp/searchMember.jsp").forward(req, resp);
            }else{
                String email = req.getParameter("memberemail");
                MemberService memberService = MemberService.getInstance();
                Set<MemberModel> memberModelSet = memberService.searchMember(email);
                req.setAttribute("SearchResultSet",memberModelSet);
                getServletContext().getRequestDispatcher("/jsp/searchResult.jsp").forward(req,resp);
            }
        }catch (Exception e){
            Logger logger = Logger.getLogger("SearchSeeker");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }
}
