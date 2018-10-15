package com.minicare.dto;

import com.minicare.model.Status;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

public class JobForm extends ActionForm {
    private String id;
    private String jobTitle;
    private String postedBy;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String startDateTime;
    private String endDateTime;
    private String payPerHour;
    private Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime()  {
        startDateTime = startDate + " " + startTime + ":00";
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime() {
        endDateTime = endDate + " " + endTime + ":00";
    }

    public String getPayPerHour() {
        return payPerHour;
    }

    public void setPayPerHour(String payPerHour) {
        this.payPerHour = payPerHour;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping , HttpServletRequest req ) {
        ActionErrors actionErrors = new ActionErrors();
        setStartDateTime();
        setEndDateTime();

        if("".equals(jobTitle)){
            actionErrors.add("JobTitleError",new ActionMessage("error.field.required"));
        }else if(!(jobTitle.trim().matches("^[A-Za-z\\s\\d]+$"))){
            actionErrors.add("JobTitleError",new ActionMessage("error.invalid"));
        }

        if("".equals(startDateTime)){
            actionErrors.add("StartDateTimeError",new ActionMessage("error.field.required"));
        }else{
            Timestamp currentDateTime = new Timestamp(System.currentTimeMillis());
            Timestamp timestamp = null;
            try {
                timestamp = Timestamp.valueOf(startDateTime);

                long milliseconds = timestamp.getTime() - currentDateTime.getTime();
                if(milliseconds<0){
                    actionErrors.add("StartDateTimeError",new ActionMessage("error.startdatetime"));
                }
            }catch (Exception e){
                actionErrors.add("StartDateTimeError",new ActionMessage("error.invalid"));
            }
        }

        if("".equals(endDateTime)){
            actionErrors.add("EndDateTimeError",new ActionMessage("error.field.required"));
        }else{
            Timestamp start = null;
            Timestamp end = null;
            try{
                start = Timestamp.valueOf(startDateTime);
                end = Timestamp.valueOf(endDateTime);

                long milliseconds = end.getTime() - start.getTime();
                if(milliseconds<0){
                    actionErrors.add("EndDateTimeError",new ActionMessage("error.enddatetime"));
                }
            }catch (Exception e){
                actionErrors.add("EndDateTimeError",new ActionMessage("error.invalid"));
            }
        }

        if("".equals(payPerHour)){
            actionErrors.add("PayPerHourError",new ActionMessage("error.field.required"));
        }else if(!payPerHour.matches("(\\d)+(\\.[0-9]+)*")){
            actionErrors.add("PayPerHourError",new ActionMessage("error.invalid"));
        }else{
            try{
                Double.parseDouble(payPerHour);
            }catch (Exception e){
                actionErrors.add("PayPerHourError",new ActionMessage("error.invalid"));
            }
        }

        return actionErrors;
    }
}
