package com.minicare.controller.sitter;

import com.minicare.dto.SitterForm;
import com.minicare.model.Member;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditAccountFormAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SitterForm sitterForm = (SitterForm) form ;
        Member member = (Member) request.getSession().getAttribute("CurrentUser");
        SitterUtil sitterUtil = SitterUtil.getInstance();
        sitterUtil.populateSitterFormBeanBySitterModel(member, sitterForm);
        return mapping.findForward("/editAccountForm");
    }

//    private void action(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            Member memberModel = (Member) req.getSession().getAttribute("CurrentUser");
//            SitterUtil sitterUtil = SitterUtil.getInstance();
//            sitterUtil.populateSitterFormBeanBySitterModel(memberModel, req);
//            getServletContext().getRequestDispatcher("/jsp/editSitterAccount.jsp").forward(req,resp);
//        }catch (Exception e){
//            Logger logger = Logger.getLogger("EditAccountFormAction");
//            logger.log(Level.SEVERE,"exception occurred",e);
//            throw new MiniCareException(e);
//        }
//    }
}
