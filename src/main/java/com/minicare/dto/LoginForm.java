package com.minicare.dto;

import com.minicare.service.VisitorService;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class LoginForm extends ActionForm {
    private String email;
    private String password;
    private String type;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping , HttpServletRequest request){
        ActionErrors actionErrors = new ActionErrors();

        if(getEmail().equals("")){
            actionErrors.add("LoginEmailError",new ActionMessage("error.field.required"));
        }else if(!getEmail().matches("^[a-zA-Z][a-zA-Z0-9]*(\\.[a-zA-Z0-9]+)*@[a-zA-Z][a-zA-Z0-9]*(\\.[a-zA-Z]+)*(\\.[a-z]{2,})$")){
            actionErrors.add("LoginEmailError",new ActionMessage("error.loginemail.format"));
        }

        if(getPassword().equals("")){
            actionErrors.add("LoginPasswordError",new ActionMessage("error.field.required"));
        }

        return actionErrors;
    }
}
