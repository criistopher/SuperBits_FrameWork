/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.email;

import com.google.common.collect.HashBiMap;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author desenvolvedor
 */
public class ConfigEmailServersProjeto {

    /**
     *
     */
    public static final String NOME_SERVIDOR_SMTP_PRINCIPAL = "smtpPrincipal";
    ServidorSaidaSmtp servidorPrincipalTransacional;

    Map<String, ServidorEmtradaPop> servidoresEntrada = new HashMap<>();
    Map<String, ServidorSaidaSmtp> servidoresSaidaSMTP = new HashMap<>();

    public ConfigEmailServersProjeto(String servidor, String Usuario, String senha) {

        servidorPrincipalTransacional = new ServidorSaidaSmtp();
        servidorPrincipalTransacional.setPorta(465);
        servidorPrincipalTransacional.setEnderecoServidor(servidor);
        servidorPrincipalTransacional.setUsuario(Usuario);
        servidorPrincipalTransacional.setSenha(senha);
        servidorPrincipalTransacional.setNome(NOME_SERVIDOR_SMTP_PRINCIPAL);
        servidoresSaidaSMTP.put(NOME_SERVIDOR_SMTP_PRINCIPAL, servidorPrincipalTransacional);

    }

    public ServidorSaidaSmtp getServidorPrincipalTransacional() {
        return servidorPrincipalTransacional;
    }

    public Map<String, ServidorEmtradaPop> getServidoresEntrada() {
        return servidoresEntrada;
    }

    public Map<String, ServidorSaidaSmtp> getServidoresSaidaSMTP() {
        return servidoresSaidaSMTP;
    }

}
