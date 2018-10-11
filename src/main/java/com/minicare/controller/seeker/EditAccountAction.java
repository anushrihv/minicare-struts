package com.minicare.controller.seeker;

import com.minicare.exception.MiniCareException;
import com.minicare.dto.SeekerForm;
import com.minicare.model.MemberModel;
import com.minicare.model.SeekerModel;
import com.minicare.service.SeekerService;
import org.apache.struts.action.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditAccountAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SeekerForm seekerForm = (SeekerForm) form ;
        HttpSession session = request.getSession(false);
        MemberModel memberModel = (MemberModel) session.getAttribute("CurrentUser");
        SeekerService seekerService = SeekerService.getInstance();
        SeekerModel seekerModel = seekerService.getSeeker(memberModel.getMemberId());

        request.setAttribute("SeekerModel",seekerModel);
        seekerModel = seekerService.editSeekerAccount(seekerForm);
        session.setAttribute("CurrentUser",seekerModel);
//        ActionMessages actionMessages = new ActionMessages();
//        actionMessages.add("HomePageMessage",new ActionMessage("message.edit.success"));
//        saveMessages(request,actionMessages);
        return mapping.findForward("/seekerhomepage");
    }

}
