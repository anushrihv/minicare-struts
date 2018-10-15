package com.minicare.dto;

import com.minicare.exception.MiniCareException;
import com.minicare.service.MemberService;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import javax.servlet.http.HttpServletRequest;

public class MemberForm extends ActionForm {
    private String memberId;
    private String firstname;
    private String lastname;
    private String phonenumber;
    private String email;
    private String address;
    private String type;
    private String password;
    private String password2;
    private String editform;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEditform() {
        return editform;
    }

    public void setEditform(String editform) {
        this.editform = editform;
    }

    public ActionErrors validate(ActionMapping mapping , HttpServletRequest req) {
        ActionErrors actionErrors = new ActionErrors();

        if("".equals(firstname)){
            actionErrors.add("FirstNameError",new ActionMessage("error.field.required"));
        }else if(!(firstname.trim().matches("^[A-Za-z]+$"))){
            actionErrors.add("FirstNameError",new ActionMessage("error.invalid"));
        }

        if("".equals(lastname)){
            actionErrors.add("LastNameError",new ActionMessage("error.field.required"));
        }else if(!(lastname.trim().matches("^[A-Za-z]+$"))){
            actionErrors.add("LastNameError",new ActionMessage("error.invalid"));
        }

        if("".equals(phonenumber)){
            actionErrors.add("PhoneNumberError",new ActionMessage("error.field.required"));
        }else if(phonenumber.trim().length()!=10){
            actionErrors.add("PhoneNumberError",new ActionMessage("error.invalid"));
        }else{
            try{
                Long.parseLong(phonenumber);
            }catch(NumberFormatException e){
                actionErrors.add("PhoneNumberError",new ActionMessage("error.invalid"));
            }
        }

        if("".equals(email)){
            actionErrors.add("EmailError",new ActionMessage("error.field.required"));
        }else if(!email.matches("^[a-zA-Z][a-zA-Z0-9]*(\\.[a-zA-Z0-9]+)*@[a-zA-Z][a-zA-Z0-9]*(\\.[a-zA-Z]+)*(\\.[a-z]{2,})$")){
            actionErrors.add("EmailError",new ActionMessage("error.invalid"));
        }

        if("".equals(address)){
            actionErrors.add("AddressError",new ActionMessage("error.field.required"));
        }

        if("".equals(password)){
            actionErrors.add("PasswordError",new ActionMessage("error.field.required"));
        }

        if(getEditform().equals("false")) {
            if ("".equals(password2)) {
                actionErrors.add("Password2Error", new ActionMessage("error.field.required"));
            } else if ((password != null) && !password.equals(password2)) {
                actionErrors.add("Password2Error", new ActionMessage("error.password.match"));
            }
        }

        return actionErrors;
    }
}
