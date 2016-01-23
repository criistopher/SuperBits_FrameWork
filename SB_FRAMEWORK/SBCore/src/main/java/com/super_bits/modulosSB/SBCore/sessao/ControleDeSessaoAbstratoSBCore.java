/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.sessao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.Controller.ControllerAppAbstratoSBCore;
import com.super_bits.Controller.Interfaces.ItfPermissao;
import com.super_bits.modulosSB.SBCore.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.sessao.Interfaces.ItfControleDeSessao;

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
             if (!usuarioEncontrado.getGrupo().isAtivo()){
                    SBCore.enviarMensagemUsuario("O Grupo de usuário está desativado", FabMensagens.ALERTA);
                    return;
             }
                
                if (!usuarioEncontrado.isAtivo()) {
                    SBCore.enviarMensagemUsuario("O Usuário está Desativado", FabMensagens.ALERTA);
                    return;
                }

                getSessaoAtual().setUsuario(usuarioEncontrado);
                SBCore.enviarAvisoAoUsuario("Bem vindo " + usuarioEncontrado.getNome());

            }
        }
        // Verificando se o usuário é um usuário adminstrador
        UsuarioSistemaRoot userSystem = new UsuarioSistemaRoot();
        if (pEmail.equals(userSystem.getEmail()) & pSenha.equals(userSystem.getSenha())) {
            getSessaoAtual().setUsuario(userSystem);
            SBCore.getCentralDeMensagens().enviaMensagem(FabMensagens.AVISO.getMsgUsuario("Você está logado como root" + usuarioEncontrado.getNome()));
            return;
        }

        SBCore.enviarMensagemUsuario("Autenticação negada", FabMensagens.ALERTA);

    }

    public void logarIDFacebook() {
        throw new UnsupportedOperationException("Não suportado ainda");

    }

}
