package com.minicare.controller.sitter;

import com.minicare.dto.PasswordHashHelper;
import com.minicare.dto.SitterForm;
import com.minicare.model.Member;
import com.minicare.model.Sitter;
import com.minicare.model.Type;
import com.minicare.service.SitterService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class SitterUtil {
    public static SitterUtil sitterUtil;

    private SitterUtil(){

    }

    static {
        sitterUtil = new SitterUtil();
    }

    public static SitterUtil getInstance(){
        return sitterUtil;
    }

    public Sitter populateSitterModel(SitterForm sitterForm,boolean isRegister) {
        Sitter sitterModel = new Sitter();
        long phoneNumber = Long.parseLong(sitterForm.getPhonenumber());
        int yearsOfExperience = Integer.parseInt(sitterForm.getYearsOfExperience());
        double expectedPay = Double.parseDouble(sitterForm.getExpectedPay());
        String passwordHash = sitterForm.getPassword();
        if(isRegister)
            passwordHash = PasswordHashHelper.get_SHA_256_SecurePassword(sitterForm.getPassword());

        if(!isRegister)
            sitterModel.setMemberId(Integer.parseInt(sitterForm.getMemberId()));
        sitterModel.setFirstName(sitterForm.getFirstname());
        sitterModel.setLastName(sitterForm.getLastname());
        sitterModel.setPhoneNumber(phoneNumber);
        sitterModel.setEmail(sitterForm.getEmail());
        sitterModel.setType(Type.SITTER);
        sitterModel.setAddress(sitterForm.getAddress());
        sitterModel.setPassword(passwordHash);
        sitterModel.setYearsOfExperience(yearsOfExperience);
        sitterModel.setExpectedPay(expectedPay);

        return sitterModel;

    }


    public void populateSitterFormBeanBySitterModel(Member member, SitterForm sitterForm) throws ClassNotFoundException, SQLException {
        SitterService sitterService = SitterService.getInstance();
        Sitter sitter = sitterService.getSitter(member.getMemberId());

        sitterForm.setMemberId(String.valueOf(sitter.getMemberId()));
        sitterForm.setType(sitter.getType().name());
        sitterForm.setFirstname(sitter.getFirstName());
        sitterForm.setLastname(sitter.getLastName());
        sitterForm.setPhonenumber(String.valueOf(sitter.getPhoneNumber()));
        sitterForm.setEmail(sitter.getEmail());
        sitterForm.setAddress(sitter.getAddress());
        sitterForm.setPassword(sitter.getPassword());
        sitterForm.setYearsOfExperience(String.valueOf(sitter.getYearsOfExperience()));
        sitterForm.setExpectedPay(String.valueOf(sitter.getExpectedPay()));
    }
}
