package com.minicare.controller.seeker;

import com.minicare.dto.SeekerForm;
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
        SeekerForm seekerForm = (SeekerForm) form ;
        Member member = (Member) request.getSession().getAttribute("CurrentUser");
        SeekerUtil seekerUtil = SeekerUtil.getInstance();
        seekerUtil.populateSeekerFormBeanBySeekerModel(member,seekerForm);
        return mapping.findForward("/editAccountForm");
    }


}
