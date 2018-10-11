package com.minicare.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    ServletContext servletContext ;

    public void init(FilterConfig filterConfig) {
        servletContext = filterConfig.getServletContext();
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,ServletException{
        HttpServletRequest request = (HttpServletRequest) servletRequest ;
        HttpServletResponse response = (HttpServletResponse) servletResponse ;
        HttpSession session = request.getSession(false);

        if(session != null){
            Object member = session.getAttribute("CurrentUser");
            if (member != null){
                filterChain.doFilter(request,response);
            }else{
                servletContext.setAttribute("Message","Please login to continue");
                response.sendRedirect("/minicare-1.0-SNAPSHOT/");
            }
        }else{
            servletContext.setAttribute("Message","Please login to continue");
            response.sendRedirect("/minicare-1.0-SNAPSHOT/");
        }
    }

    public void destroy() {

    }
}
