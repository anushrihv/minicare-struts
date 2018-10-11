package com.minicare.controller.seeker;

import com.minicare.exception.MiniCareException;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SeekerHomePage extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            return mapping.findForward("SeekerHome");
        }catch(Exception e){
            String message = e.getMessage();
            throw new MiniCareException(message);
        }
    }


}
