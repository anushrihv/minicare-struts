package com.minicare.controller.seeker;

import com.minicare.dto.SeekerForm;
import com.minicare.exception.MiniCareException;
import com.minicare.model.MemberModel;
import com.minicare.service.SeekerService;
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

public class EditAccountFormAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SeekerForm seekerForm = (SeekerForm) form ;
        MemberModel memberModel = (MemberModel) request.getSession().getAttribute("CurrentUser");
        SeekerUtil seekerUtil = SeekerUtil.getInstance();
        seekerUtil.populateSeekerFormBeanBySeekerModel(memberModel,seekerForm);
        return mapping.findForward("/editAccountForm");
    }


}
