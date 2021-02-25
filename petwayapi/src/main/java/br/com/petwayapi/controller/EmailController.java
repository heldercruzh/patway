package br.com.petwayapi.controller;

import br.com.petwayapi.models.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.MimeMessageHelper;
import br.com.petwayapi.models.EmailRequest;
import javax.mail.internet.MimeMessage;

@RestController
public class EmailController {

    @Autowired private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    @RequestMapping(path = "/email-send", method = RequestMethod.POST)
    public void sendMail(@RequestBody EmailRequest email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(remetente);
            message.setTo(email.getDestinatario());
            message.setSubject(email.getAssunto());
            message.setText(email.getTexto());

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "/email-send-html", method = RequestMethod.POST)
    public void sendMailHtml(@RequestBody EmailRequest email) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper message = new MimeMessageHelper( mail );
            message.setFrom(remetente);
            message.setTo(email.getDestinatario());
            message.setSubject(email.getAssunto());
            message.setText(email.getTexto());

            mailSender.send(mail);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}