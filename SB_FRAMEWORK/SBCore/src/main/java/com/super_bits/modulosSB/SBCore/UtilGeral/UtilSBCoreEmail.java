/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Classe de UTILITÀRIOS (Métodos EStáticos commmente Utilizados)____________
 * Função: Lidar com envio de Email
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 */
public abstract class UtilSBCoreEmail {

    public static boolean enviaporSSL(final String servidor, final String pUsuario, final String pSenha, String mensagem, String para, String pAssunto) {

        return enviarEmail(getPropriedadesEmailSMTP(servidor), pUsuario, pSenha, mensagem, para, pAssunto);
    }

    private static Properties getPropriedadesEmailSMTP(String enderecoServidorSMTP) {
        Properties props = new Properties();
        props.put("mail.smtp.host", enderecoServidorSMTP);
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        return props;
    }

    private static boolean enviarEmail(Properties props, final String pUsuario, final String pSenha, String mensagem, String para, String pAssunto) {
        boolean envioucomsucesso = true;
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(pUsuario, pSenha);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(pUsuario));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(para));
            message.setSubject(pAssunto);
            message.setText(mensagem);

            Transport.send(message);

        } catch (MessagingException e) {

            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro enviando e-mail", e);
            envioucomsucesso = false;
        }
        return envioucomsucesso;
    }

    /**
     *
     * @param pUsuario email do usuário
     * @param pSenha senha do usuário
     * @param mensagem mensagem enviada
     * @param para email do destinatario
     * @param pAssunto Assunto do e-mail
     * @return
     */
    public static boolean enviaGmailporSSL(final String pUsuario, final String pSenha, String mensagem, String para, String pAssunto) {

        Properties props = getPropriedadesEmailSMTP("smtp.gmail.com");
        return enviarEmail(props, pUsuario, pSenha, mensagem, para, pAssunto);

    }

}
