/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.sessao;

import com.super_bits.modulosSB.SBCore.modulos.Controller.ControllerAppAbstratoSBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfPermissao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreEmail;
import com.super_bits.modulosSB.SBCore.modulos.sessao.Interfaces.ItfControleDeSessao;

/**
 *
 *
 *
 * @author Salvio
 */
public abstract class ControleDeSessaoAbstratoSBCore implements ItfControleDeSessao {

    @Override
    public void registrarAcao(ItfPermissao pAcesso) {
        getSessaoAtual().getAcoesRealizadas().add(pAcesso);
    }

    @Override
    public void logarEmailESenha(String pEmail, String pSenha) {
        ItfUsuario usuarioEncontrado = ControllerAppAbstratoSBCore.getUsuarioByEmail(pEmail);

        if (usuarioEncontrado != null) {
            if (usuarioEncontrado.getSenha().equals(pSenha)) {
                if (!usuarioEncontrado.getGrupo().isAtivo()) {
                    SBCore.enviarMensagemUsuario("O Grupo de usuário está desativado", FabMensagens.ALERTA);
                    return;
                }

                if (!usuarioEncontrado.isAtivo()) {
                    SBCore.enviarMensagemUsuario("Atenção, O Usuário " + usuarioEncontrado.getNome() + "está Desativado", FabMensagens.ALERTA);
                    //  return;
                }

                getSessaoAtual().setUsuario(usuarioEncontrado);
                SBCore.enviarAvisoAoUsuario("Bem vindo " + usuarioEncontrado.getNome());
                return;
            }
        }
        // Verificando se o usuário é um usuário adminstrador
        UsuarioSistemaRoot userSystem = new UsuarioSistemaRoot();
        if (pEmail.equals(userSystem.getEmail()) & pSenha.equals(userSystem.getSenha())) {
            getSessaoAtual().setUsuario(userSystem);
            SBCore.enviarAvisoAoUsuario("Você está logado como root");
            return;
        }

        SBCore.enviarMensagemUsuario("Autenticação negada", FabMensagens.ALERTA);

    }

    public void logarIDFacebook() {
        throw new UnsupportedOperationException("Não suportado ainda");

    }

    protected void enviarSenhaParaEmail(String pEmail) {

        ItfUsuario usuarioEncontrado = ControllerAppAbstratoSBCore.getUsuarioByEmail(pEmail);

        if (usuarioEncontrado == null) {
            SBCore.enviarMensagemUsuario("O email" + pEmail + " não foi encontrado no sistema", FabMensagens.AVISO);
            return;
        }
        if (usuarioEncontrado != null) {

            if (UtilSBCoreEmail.enviaGmailporSSL("guiasemktdigitalcontagem@gmail.com", "vidanova@2015", "Olá, conforme solicitação, segue a senha,<br/> mas antes, como esssa "
                    + "é uma mensagem provisória não posso deixar de observar a completa falta de senso nexo e conformidade com a realidade, um ser humano viver esquecendo sua senha...  <br/>"
                    + "Mas blz, dessa vez passa a senha é " + usuarioEncontrado.getSenha(), pEmail, "Recuperação de senha")) {
                SBCore.enviarAvisoAoUsuario("Um e-mail com a senha foi enviado para " + pEmail);
            } else {
                SBCore.enviarAvisoAoUsuario("Um erro ocorreu ao tentar enviar o e-mail com a senha para: " + pEmail + " entre em contato conosco para recuperar a senha");
            }
        }
    }

}
