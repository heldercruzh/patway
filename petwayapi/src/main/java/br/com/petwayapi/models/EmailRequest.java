package br.com.petwayapi.models;

import org.springframework.mail.SimpleMailMessage;

public class EmailRequest {

    private String[] destinatario;
    private String assunto;
    private String texto;

    public EmailRequest(String[] destinatario, String assunto, String texto) {
        this.destinatario = destinatario;
        this.assunto = assunto;
        this.texto = texto;
    }

    public String[] getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String[] destinatario) {
        this.destinatario = destinatario;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}

