package com.minicare.dto;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class ApplyJobForm extends ActionForm {
    String id;
    String expectedPay;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(String expectedPay) {
        this.expectedPay = expectedPay;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors actionErrors = new ActionErrors();

        if(getExpectedPay().equals("")){
            actionErrors.add("ExpectedPayError",new ActionMessage("error.field.required"));
        }else{
            try {
                Double.parseDouble(getExpectedPay());
            }catch (Exception e){
                actionErrors.add("ExpectedPayError",new ActionMessage("error.invalid"));
            }
        }
        return actionErrors;
    }
}
