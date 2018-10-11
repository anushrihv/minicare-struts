package com.minicare.controller.visitor;

import com.minicare.exception.MiniCareException;
import com.minicare.dto.LoginForm;
import com.minicare.service.VisitorService;
import org.apache.struts.action.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LoginAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            ActionErrors actionErrors = new ActionErrors();
            LoginForm loginForm = (LoginForm) form;
            VisitorService visitorService = VisitorService.getInstance();
            VisitorUtil visitorUtil = VisitorUtil.getInstance();
            if (!(visitorService.authenticate(request, loginForm))) {
                actionErrors.add("LoginEmailError",new ActionMessage("error.login.email.password"));
                saveErrors(request,actionErrors);
                return mapping.findForward("WelcomePage");
            } else {
                String type = loginForm.getType();
                String email = loginForm.getEmail();
                HttpSession session = request.getSession();
                visitorUtil.populateModelFromDb(email, session);
                if (type.equals("SITTER")) {
                    return mapping.findForward("SitterHomePage");
                } else
                    return mapping.findForward("SeekerHomePage");
            }
        } catch (Exception e) {
            Logger logger = Logger.getLogger("LoginAction");
            logger.log(Level.SEVERE, "exception occurred", e);
            throw new MiniCareException(e);
        }

    }
}


