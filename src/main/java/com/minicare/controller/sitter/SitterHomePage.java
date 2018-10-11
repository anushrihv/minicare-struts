package com.minicare.controller.sitter;

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
import java.util.logging.Level;
import java.util.logging.Logger;

public class SitterHomePage extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            return mapping.findForward("SitterHome");
        }catch(Exception e){
            Logger logger = Logger.getLogger("SitterHomePage");
            logger.log(Level.SEVERE,"Exception occurred",e);
            throw new MiniCareException(e);
        }
    }

}

