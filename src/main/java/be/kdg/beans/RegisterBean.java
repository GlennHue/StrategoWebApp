package be.kdg.beans;


import be.kdg.service.api.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by wouter on 6/02/14.
 */
@Component("registerBean")
@Scope("request")
public class RegisterBean implements Serializable {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    @Autowired
    private UserServiceApi userService;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String addNewUser(){
        if(password.equals(confirmPassword))
        {
            userService.addUser(username,password,email);
            sendVerificationEmail();
            return "emailSend";
        }
        return null;
    }
        //TODO: use a template email ? (see mkyong)
    private void sendVerificationEmail() {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-mail.xml");
        MailSender mailSender = (MailSender)context.getBean("mailSender");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply.stratego@gmail.com");
        message.setTo(email);
        message.setSubject("Email verification");
        String uuid = UUID.randomUUID().toString();
        String hyperlink = "http://localhost:8080/verify?uuid=" + uuid;
        userService.setAuthenticationCode(username,uuid);
        message.setText("Thank you for creating a Stratego account!\nNow we just need you to verify your e-mail address by clicking this link:\n" + hyperlink + "\nSincerely\nThe Stratego support team");
        mailSender.send(message);
    }

}