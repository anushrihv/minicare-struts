package com.minicare.dto;

import com.minicare.model.Status;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

public class JobFormBean implements ValidationForm {
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

    public boolean validate(HttpServletRequest req) {
        boolean status=true;

        if("".equals(jobTitle)){
            req.setAttribute("JobTitleError","Required field");
            status = false;
        }else if(!(jobTitle.trim().matches("^[A-Za-z\\s\\d]+$"))){
            req.setAttribute("JobTitleError","Job Title must have alphabets only");
            status=false;
        }

        if("".equals(startDateTime)){
            req.setAttribute("StartDateTimeError","Required field");
            status = false;
        }else{
            Timestamp currentDateTime = new Timestamp(System.currentTimeMillis());
            Timestamp timestamp = null;
            try {
                timestamp = Timestamp.valueOf(startDateTime);

                long milliseconds = timestamp.getTime() - currentDateTime.getTime();
                if(milliseconds<0){
                    req.setAttribute("StartDateTimeError","Start Date Time must be greater than current Date Time");
                    status=false;
                }
            }catch (Exception e){
                req.setAttribute("StartDateTimeError","Invalid input");
                status=false;
            }
        }

        if("".equals(endDateTime)){
            req.setAttribute("EndDateTimeError","Required field");
            status = false;
        }else{
            Timestamp start = null;
            Timestamp end = null;
            try{
                start = Timestamp.valueOf(startDateTime);
                end = Timestamp.valueOf(endDateTime);

                long milliseconds = end.getTime() - start.getTime();
                if(milliseconds<0){
                    req.setAttribute("EndDateTimeError","End Date Time must be greater than Start Date Time");
                    status=false;
                }
            }catch (Exception e){
                req.setAttribute("EndDateTimeError","Invalid input");
                status = false;
            }
        }

        if("".equals(payPerHour)){
            req.setAttribute("PayPerHourError","Required field");
            status = false;
        }else if(!payPerHour.matches("(\\d)+(\\.[0-9]+)*")){
            req.setAttribute("PayPerHourError","Invalid Input");
            status=false;
        }else{
            try{
                Double.parseDouble(payPerHour);
            }catch (Exception e){
                req.setAttribute("PayPerHourError","Invalid input");
                status = false;
            }
        }

        return status;
    }
}
