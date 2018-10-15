package com.minicare.controller.seeker;

import com.minicare.dto.SeekerForm;
import com.minicare.model.Member;
import com.minicare.model.Seeker;
import com.minicare.service.SeekerService;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditAccountAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SeekerForm seekerForm = (SeekerForm) form ;
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("CurrentUser");
        SeekerService seekerService = SeekerService.getInstance();
        Seeker seekerModel = seekerService.getSeeker(member.getMemberId());

        request.setAttribute("Seeker",seekerModel);
        seekerModel = seekerService.editSeekerAccount(seekerForm);
        session.setAttribute("CurrentUser",seekerModel);
        ActionMessages actionMessages = new ActionMessages();
        actionMessages.add("HomePageMessage",new ActionMessage("message.edit.success"));
        saveMessages(request,actionMessages);
        return mapping.findForward("/seekerhomepage");
    }

}
