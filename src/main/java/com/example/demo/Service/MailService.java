package com.example.demo.Service;

import com.example.demo.Entity.EmailNotification;
import com.example.demo.Exceptions.BlogPortException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
class MailService {

//@Autowired
//EmailNotification emailNotification;
//@Autowired
// MailContentBuilder mailContentBuilder;
@Autowired
JavaMailSender mailSender;
    @Async
    void sendMail(EmailNotification emailNotification) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("BlogPort@email.com");
            messageHelper.setTo(emailNotification.getRecipient());
            messageHelper.setSubject(emailNotification.getSubject());
            messageHelper.setText(emailNotification.getBody());
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Activation email sent!!");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new BlogPortException("Exception occurred when sending mail to " + emailNotification.getRecipient(), e);
        }
    }

}