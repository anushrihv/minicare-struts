package com.minicare.controller.member;

import com.minicare.dto.ResetPasswordForm;
import com.minicare.exception.MiniCareException;
import com.minicare.dto.PasswordHashHelper;
import com.minicare.model.Member;
import com.minicare.model.Type;
import com.minicare.service.MemberService;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResetPassword extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try{
            ResetPasswordForm resetPasswordForm = (ResetPasswordForm) form ;
            MemberService memberService = MemberService.getInstance();
            Member member = (Member) req.getSession(false).getAttribute("CurrentUser");
            ActionErrors actionErrors ;
            if(!(actionErrors = memberService.checkPassword(req)).isEmpty()){
                saveErrors(req,actionErrors);
                return mapping.findForward("/resetpasswordform");
            }else{
                memberService.updatePassword(member.getMemberId(),resetPasswordForm.getNewpassword());
                String newPasswordHash = PasswordHashHelper.get_SHA_256_SecurePassword(resetPasswordForm.getNewpassword());
                member.setPassword(newPasswordHash);
                req.getSession().setAttribute("CurrentUser", member);
                ActionMessages actionMessages = new ActionMessages();
                actionMessages.add("HomePageMessage", new ActionMessage("message.password.update"));
                saveMessages(req , actionMessages);
                if(member.getType().name().equals(Type.SITTER.name())){
                    return mapping.findForward("SitterHomePage");
                }else{
                    return mapping.findForward("SeekerHomePage");
                }
            }

        }catch (Exception e){
            Logger logger = Logger.getLogger("ResetPassword");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw  new MiniCareException(e);
        }
    }
}
