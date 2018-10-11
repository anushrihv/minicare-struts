package com.minicare.dto;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class SeekerEditAccountForm extends ActionForm {
    String firstName;
    String lastName;
    String phoneNumber;
    String address;
    String numberOfChildren;
    String spouseName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors actionErrors = new ActionErrors();
        if("".equals(firstName)){
            actionErrors.add("FirstNameError",new ActionMessage("error.field.required"));
        }else if(!(firstName.trim().matches("^[A-Za-z]+$"))){
            actionErrors.add("FirstNameError",new ActionMessage("error.invalid"));
        }

        if("".equals(lastName)){
            actionErrors.add("LastNameError",new ActionMessage("error.field.required"));
        }else if(!(lastName.trim().matches("^[A-Za-z]+$"))){
            actionErrors.add("LastNameError",new ActionMessage("error.invalid"));
        }

        if("".equals(phoneNumber)){
            actionErrors.add("PhoneNumberError",new ActionMessage("error.field.required"));
        }else if(phoneNumber.trim().length()!=10){
            actionErrors.add("PhoneNumberError",new ActionMessage("error.invalid"));
        }else{
            try{
                Long.parseLong(phoneNumber);
            }catch(NumberFormatException e){
                actionErrors.add("PhoneNumberError",new ActionMessage("error.invalid"));
            }
        }

        if("".equals(address)){
            actionErrors.add("AddressError",new ActionMessage("error.field.required"));
        }

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
