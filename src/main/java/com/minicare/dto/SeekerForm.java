package com.minicare.dto;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class SeekerForm extends MemberForm {
    private String numberOfChildren;
    private String spouseName;

    public String getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(String numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping , HttpServletRequest req){
        ActionErrors actionErrors = super.validate(mapping,req);

        if(numberOfChildren.equals("")){

        }else if(numberOfChildren.matches("(\\D)*")){
            actionErrors.add("NumberOfChildrenError",new ActionMessage("error.invalid"));
        }else {
            try {
                int num = Integer.parseInt(numberOfChildren);
                if (num > 69) {
                    actionErrors.add("NumberOfChildrenError",new ActionMessage("error.invalid"));
                }
            }catch (NumberFormatException e){
                actionErrors.add("NumberOfChildrenError",new ActionMessage("error.invalid"));
            }
        }

        if(spouseName.equals("")){

        }else if(!(spouseName.trim().matches("^[A-Za-z]+$"))){
            actionErrors.add("SpouseNameError",new ActionMessage("error.invalid"));
        }

        return actionErrors;
    }
}
