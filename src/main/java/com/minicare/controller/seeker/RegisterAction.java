package com.minicare.controller.seeker;

import com.minicare.dto.SeekerForm;
import com.minicare.service.MemberService;
import com.minicare.service.VisitorService;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        MemberService memberService = MemberService.getInstance();
        VisitorService visitorService = VisitorService.getInstance();
        SeekerForm seekerForm = (SeekerForm) form;
        ActionErrors actionErrors = new ActionErrors();

        if(!memberService.uniqueEmail(seekerForm.getEmail())){
            actionErrors.add("EmailError",new ActionMessage("error.email.exists"));
            return mapping.findForward("SeekerRegister");
        } else {
            HttpSession session = request.getSession();
            visitorService.storeSeekerDetails(seekerForm,session);
            return mapping.findForward("SeekerHomePage");
        }

    }
}
