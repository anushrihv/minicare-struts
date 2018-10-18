package com.minicare.controller.member;

import com.minicare.dto.SearchForm;
import com.minicare.exception.MiniCareException;
import com.minicare.model.Member;
import com.minicare.service.MemberService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchMember extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            SearchForm searchForm = (SearchForm) form ;
            String email = searchForm.getEmail();
            MemberService memberService = MemberService.getInstance();
            Member currentUser = (Member) req.getSession().getAttribute("CurrentUser");
            List<Member> memberList = memberService.searchMember(email,currentUser.getType());
            req.setAttribute("SearchResultSet", memberList);
            return mapping.findForward("/searchresult");

        }catch (Exception e){
            Logger logger = Logger.getLogger("SearchSeeker");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }

//    private void action(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            String url = String.valueOf(req.getRequestURL());
//            if (url.contains("searchform")) {
//                getServletContext().getRequestDispatcher("/jsp/searchMember.jsp").forward(req, resp);
//            }else{
//                String email = req.getParameter("memberemail");
//                MemberService memberService = MemberService.getInstance();
//                Set<Member> memberModelSet = memberService.searchMember(email);
//                req.setAttribute("SearchResultSet",memberModelSet);
//                getServletContext().getRequestDispatcher("/jsp/searchResult.jsp").forward(req,resp);
//            }
//        }catch (Exception e){
//            Logger logger = Logger.getLogger("SearchSeeker");
//            logger.log(Level.SEVERE,"exception occurred",e);
//            throw new MiniCareException(e);
//        }
//    }
}
