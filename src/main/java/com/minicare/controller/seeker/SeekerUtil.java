package com.minicare.controller.seeker;

import com.minicare.dto.PasswordHashHelper;
import com.minicare.dto.SeekerForm;
import com.minicare.model.Member;
import com.minicare.model.Seeker;
import com.minicare.model.Type;
import com.minicare.service.SeekerService;

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
        Seeker seekerModel = new Seeker();
        int numberOfChildren ;
        try{
            numberOfChildren = Integer.parseInt(seekerForm.getNumberOfChildren());
        }catch (Exception e){
            numberOfChildren = 0;
        }
        long phoneNumber = Long.parseLong(seekerForm.getPhonenumber());
        String passwordHash = seekerForm.getPassword();
        if(isRegister)
            passwordHash = PasswordHashHelper.get_SHA_256_SecurePassword(seekerForm.getPassword());

        if(!isRegister)
            seekerModel.setMemberId(Integer.parseInt(seekerForm.getMemberId()));
        seekerModel.setFirstName(seekerForm.getFirstname());
        seekerModel.setLastName(seekerForm.getLastname());
        seekerModel.setPhoneNumber(phoneNumber);
        seekerModel.setEmail(seekerForm.getEmail());
        seekerModel.setType(Type.SEEKER);
        seekerModel.setAddress(seekerForm.getAddress());
        seekerModel.setPassword(passwordHash);
        seekerModel.setNumberOfChildren(numberOfChildren);
        seekerModel.setSpouseName(seekerForm.getSpouseName());

        return seekerModel;
    }


    public void populateSeekerFormBeanBySeekerModel(Member member, SeekerForm seekerForm) {
        SeekerService seekerService = SeekerService.getInstance();
        Seeker seeker = seekerService.getSeeker(member.getMemberId());

        seekerForm.setMemberId(String.valueOf(seeker.getMemberId()));
        seekerForm.setFirstname(seeker.getFirstName());
        seekerForm.setLastname(seeker.getLastName());
        seekerForm.setPhonenumber(String.valueOf(seeker.getPhoneNumber()));
        seekerForm.setEmail(seeker.getEmail());
        seekerForm.setAddress(seeker.getAddress());
        seekerForm.setPassword(seeker.getPassword());
        seekerForm.setType(Type.SEEKER.name());
        seekerForm.setSpouseName(seeker.getSpouseName());
        seekerForm.setNumberOfChildren(String.valueOf(seeker.getNumberOfChildren()));
    }

}
