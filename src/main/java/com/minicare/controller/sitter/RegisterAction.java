package com.minicare.controller.sitter;

import com.minicare.dto.LoginForm;
import com.minicare.dto.SitterForm;
import com.minicare.exception.MiniCareException;
import com.minicare.service.MemberService;
import com.minicare.service.VisitorService;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            VisitorService visitorService = VisitorService.getInstance();
            MemberService memberService = MemberService.getInstance();
            SitterForm sitterForm = (SitterForm)form;
            ActionErrors actionErrors = new ActionErrors();

            if (!memberService.uniqueEmail(sitterForm.getEmail())) {
                actionErrors.add("EmailError", new ActionMessage("error.email.exists"));
                saveErrors(request,actionErrors);
                return mapping.findForward("SitterRegister");
            }else {
                HttpSession session = request.getSession();
                visitorService.storeSitterDetails(sitterForm, session);
                return mapping.findForward("SitterHomePage");
            }

        }catch (Exception e){
            Logger logger = Logger.getLogger("SitterRegisterAction");
            logger.log(Level.SEVERE,"Exception occurred",e);
            throw new MiniCareException(e);
        }
    }
}
