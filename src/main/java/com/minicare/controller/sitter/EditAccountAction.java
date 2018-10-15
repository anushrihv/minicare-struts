package com.minicare.controller.sitter;

import com.minicare.dto.SitterForm;
import com.minicare.model.Member;
import com.minicare.model.Sitter;
import com.minicare.service.SitterService;
import org.apache.struts.action.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class EditAccountAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        SitterForm sitterForm = (SitterForm) form ;
        HttpSession session = req.getSession(false);
        Member member = (Member) session.getAttribute("CurrentUser");
        SitterService sitterService = SitterService.getInstance();
        Sitter sitterModel = sitterService.getSitter(member.getMemberId());
        req.setAttribute("Sitter",sitterModel);
        sitterModel = sitterService.editSitterAccount(sitterForm);
        session.setAttribute("CurrentUser",sitterModel);
        ActionMessages actionMessages = new ActionMessages();
        actionMessages.add("HomePageMessage",new ActionMessage("message.edit.success"));
        saveMessages(req,actionMessages);
        return mapping.findForward("/sitterhomepage");
    }

}
