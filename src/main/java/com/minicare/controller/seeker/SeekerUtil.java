package com.minicare.controller.seeker;

import com.minicare.dto.PasswordHashHelper;
import com.minicare.dto.SeekerForm;
import com.minicare.model.Member;
import com.minicare.model.Seeker;
import com.minicare.model.Type;
import com.minicare.service.SeekerService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class SeekerUtil {
    public static SeekerUtil seekerUtil;

    static {
        seekerUtil = new SeekerUtil();
    }

    private SeekerUtil(){

    }

    public static SeekerUtil getInstance(){
        return seekerUtil;
    }


    public Seeker populateSeekerModel(SeekerForm seekerForm , boolean isRegister) {
        //SeekerForm seekerForm = populateSeekerFormBean(req);
        //SeekerForm seekerForm = (SeekerForm) req.getAttribute("SeekerForm");
        Seeker seekerModel = new Seeker();
        int numberOfChildren;
        int memberId = Integer.parseInt(seekerForm.getMemberId());
        long phoneNumber = Long.parseLong(seekerForm.getPhonenumber());

        String passwordHash = PasswordHashHelper.get_SHA_256_SecurePassword(seekerForm.getPassword());
        try {
            numberOfChildren = Integer.parseInt(seekerForm.getNumberOfChildren());
        }catch(NumberFormatException e){
            numberOfChildren = 0;
        }


        if(!isRegister)
            seekerModel.setMemberId(memberId);
        seekerModel.setFirstName(seekerForm.getFirstname());
        seekerModel.setLastName(seekerForm.getLastname());
        seekerModel.setPhoneNumber(phoneNumber);
        seekerModel.setEmail(seekerForm.getEmail());
        seekerModel.setType(Type.SEEKER);
        seekerModel.setAddress(seekerForm.getAddress());
        seekerModel.setPassword(passwordHash);
        seekerModel.setNumberOfChildren(numberOfChildren);
        seekerModel.setSpouseName(seekerForm.getSpouseName());

        //req.setAttribute("Seeker",seekerModel);
        return seekerModel;
    }


    public void populateSeekerFormBeanBySeekerModel(Member member, SeekerForm seekerForm) throws ClassNotFoundException, SQLException {
        SeekerService seekerService = SeekerService.getInstance();

        seekerForm.setMemberId(String.valueOf(member.getMemberId()));
        seekerForm.setFirstname(member.getFirstName());
        seekerForm.setLastname(member.getLastName());
        seekerForm.setPhonenumber(String.valueOf(member.getPhoneNumber()));
        seekerForm.setEmail(member.getEmail());
        seekerForm.setAddress(member.getAddress());
        seekerForm.setPassword(member.getPassword());
        seekerForm.setType(Type.SEEKER.name());

        Seeker seekerModel = seekerService.getSeeker(member.getMemberId());

        seekerForm.setSpouseName(seekerModel.getSpouseName());
        seekerForm.setNumberOfChildren(String.valueOf(seekerModel.getNumberOfChildren()));
    }

}
