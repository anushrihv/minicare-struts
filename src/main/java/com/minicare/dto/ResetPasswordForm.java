package com.minicare.dto;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class ResetPasswordForm extends ActionForm {
    String oldpassword;
    String newpassword;
    String newpassword2;

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getNewpassword2() {
        return newpassword2;
    }

    public void setNewpassword2(String newpassword2) {
        this.newpassword2 = newpassword2;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors actionErrors = new ActionErrors();

            if (getOldpassword().equals("")) {
                actionErrors.add("OldPasswordError", new ActionMessage("error.field.required"));
            }

            if (getNewpassword().equals("")) {
                actionErrors.add("NewPasswordError", new ActionMessage("error.field.required"));
            }

            if (getNewpassword2().equals("")) {
                actionErrors.add("NewPassword2Error", new ActionMessage("error.field.required"));
            }
            return actionErrors;
    }
}
