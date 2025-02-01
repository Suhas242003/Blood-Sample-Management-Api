package com.example.bsm.mail;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
@Builder
@AllArgsConstructor

public class MailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public void sendMail(String to,String subject,String text) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper= new MimeMessageHelper(message,true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text,true);
        javaMailSender.send(message);
    }
    public String genericContext(String templateName, Map<String,Object> variable){
        Context context = new Context();
        context.setVariables(variable);
       return  templateEngine.process(templateName,context);

    }
}
