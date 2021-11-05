package com.sydney.vacbook.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class SendEmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendEmail(String to, String body, String topic){
        System.out.println("sending msg email!");
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(topic);
        msg.setText(body);

        javaMailSender.send(msg);
    }

    public void sendHtmlEmail(String to, String body, String topic/*, String rscPath, String rscId*/) throws MessagingException {
        System.out.println("sending html email!");
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(topic);
        Context context = new Context();
        String text = templateEngine.process("userPages/subscriptionEmail",context);



        mimeMessageHelper.setText(text,true);
        javaMailSender.send(message);
    }

}
