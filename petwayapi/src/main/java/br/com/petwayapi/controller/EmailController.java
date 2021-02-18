package br.com.petwayapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

@RestController
public class EmailController {

    @Autowired private JavaMailSender mailSender;

    @RequestMapping(path = "/email-send", method = RequestMethod.GET)
    public String sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("testeenvioemail486@gmail.com");
        message.setTo("heldercruzh@gmail.com");
        message.setSubject("teste de email assunto");
        message.setText("teste de email corpo");

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }

    @RequestMapping(path = "/email-send-html", method = RequestMethod.GET)
    public String sendMailHtml() {
        try {
            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper message = new MimeMessageHelper( mail );
            message.setFrom("testeenvioemail486@gmail.com");
            message.setTo("heldercruzh@gmail.com");
            message.setSubject("teste de email assunto html");
            message.setText("teste de email corpo html");

            mailSender.send(mail);

            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar e-mail";
        }
    }
}