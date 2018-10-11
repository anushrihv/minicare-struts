package com.minicare.dto;

import com.minicare.exception.MiniCareException;
import com.minicare.service.MemberService;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class SitterForm extends MemberForm {
    private String yearsOfExperience;
    private String expectedPay;

    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(String expectedPay) {
        this.expectedPay = expectedPay;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping , HttpServletRequest req){
        ActionErrors actionErrors = super.validate(mapping,req);

        if(yearsOfExperience.equals("")){
           actionErrors.add("YearsOfExperienceError",new ActionMessage("error.field.required"));
       }else if(yearsOfExperience.matches("(\\D)*")){
           actionErrors.add("YearsOfExperienceError",new ActionMessage("error.invalid"));
       }else{
            try {
                int num = Integer.parseInt(yearsOfExperience);
                if (num > 122) {
                    actionErrors.add("YearsOfExperienceError",new ActionMessage("error.invalid"));
                }
            }catch(NumberFormatException e){
                actionErrors.add("YearsOfExperienceError",new ActionMessage("error.invalid"));
            }
        }

       if(expectedPay.equals("")){
            actionErrors.add("ExpectedPayError",new ActionMessage("error.field.required"));
       }else if(!expectedPay.trim().matches("^[0-9]+(\\.[0-9]+)*$")){
            actionErrors.add("ExpectedPayError",new ActionMessage("error.invalid"));
       }else{

        }

        try {
            if (actionErrors.isEmpty()) {
                MemberService memberService = MemberService.getInstance();
                if (!memberService.uniqueEmail(getEmail())) {
                    actionErrors.add("EmailError", new ActionMessage("error.email.exists"));
                }
            }
        }catch (Exception e){
            throw new MiniCareException(e);
        }

       return actionErrors;
    }
}
