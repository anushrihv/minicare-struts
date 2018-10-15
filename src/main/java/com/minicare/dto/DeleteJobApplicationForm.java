package com.minicare.dto;

import org.apache.struts.action.ActionForm;

public class DeleteJobApplicationForm extends ActionForm {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
