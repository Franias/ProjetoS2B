/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author gustavo
 */
public class Email {

    private static SimpleEmail emailNotificacao;
    //hostname do servidor de email,usuário e senha
    private static final String HOSTNAME = "smtp.gmail.com";
    private static final int PORTA = 587;
    //para teste coloque o seu email do google
    private static final String USER = "seu nome usuário";
    //senha do email do google
    private static final String PASSWORD = "sua senha";
    //seu suário de email
    private static final String EMAILUSER = "seu email";

    /**
     *
     * @param titulo título do email
     * @param msg Mensagem a ser enviada
     * @param remetente1 email do remetente1
     * @param remetente2 email do remetente2
     * @throws org.apache.commons.mail.EmailException Erro email não enviado
     */
    public static void enviarEmail(String titulo,
            String msg, String remetente1, String remetente2) throws EmailException {

        String [] remetentes = {remetente1,remetente2};
        
        emailNotificacao = new SimpleEmail();
        //servidor de mail e porta
        emailNotificacao.setHostName(HOSTNAME);
        emailNotificacao.setSmtpPort(PORTA);
        //protocolo de envio
        emailNotificacao.setStartTLSEnabled(true);
        emailNotificacao.setAuthentication(USER, PASSWORD);
        emailNotificacao.setFrom(EMAILUSER); //aqui necessita ser o email que voce fara a autenticacao
        emailNotificacao.setSubject(titulo);
        emailNotificacao.setMsg(msg);
        emailNotificacao.addTo(remetentes); //pode ser qualquer um email
        emailNotificacao.send();//envia email

    }

}
