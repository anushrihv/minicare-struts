package com.minicare.filter;

import com.minicare.exception.MiniCareException;
import com.minicare.model.MemberModel;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthorizationFilter implements Filter {
    ServletContext servletContext;

    public void init(FilterConfig filterConfig) {
        servletContext = filterConfig.getServletContext();
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain){
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String url = String.valueOf(request.getRequestURL());
            HttpSession session = request.getSession(false);
            MemberModel memberModel = (MemberModel) session.getAttribute("CurrentUser");
            String type = memberModel.getType().name();
            if(url.contains("/sitter") && type.equals("SEEKER")){

                response.sendRedirect("/minicare-1.0-SNAPSHOT/seeker/homepage.do");
            }else if(url.contains("/seeker") && type.equals("SITTER")){

                response.sendRedirect("/minicare-1.0-SNAPSHOT/sitter/homepage.do");
            }else {
                filterChain.doFilter(request, response);
            }

        }catch (Exception e){
            Logger logger = Logger.getLogger("AuthorizationFilter");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }

    public void destroy() {

    }
}
