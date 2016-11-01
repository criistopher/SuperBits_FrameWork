/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import javax.swing.JOptionPane;

/**
 *
 * @author salvioF
 */
public class CentralComunicacaoDesktop implements ItfCentralComunicacao {

    @Override
    public FabTipoRespostaComunicacao iniciarComunicacaoSistema_Usuairo(FabTipoInicioComunicacao tipocomunicacao, String mensagem) {
        switch (tipocomunicacao) {
            case NOTIFICAR:
                break;
            case PERGUNTAR_SIM_OU_NAO:
                int dialogResult = JOptionPane.showConfirmDialog(null,
                        mensagem, "Deseja continuar?", JOptionPane.YES_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    return FabTipoRespostaComunicacao.SIM;
                } else {
                    System.out.println("não");
                    return FabTipoRespostaComunicacao.NAO;
                }

            case PERGUNTAR_SIM_NAO_IGNORAR:
                break;
            case PERGUNTAR_SIM_NAO_MAIS_TARDE:
                break;
            case SOLICITAR_AUTORIZACAO:
                break;
            default:
                throw new AssertionError(tipocomunicacao.name());

        }
        return FabTipoRespostaComunicacao.NAO_ENTENDIDO;
    }

    @Override
    public FabTipoRespostaComunicacao iniciarComunicacaoEntre_Usuairos(FabTipoInicioComunicacao tipocomunicacao, ItfUsuario pRemetente, ItfUsuario pDestinatario, String mensagem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FabTipoRespostaComunicacao iniciarComunicacaoUsuairo_paraGrupo(FabTipoInicioComunicacao tipocomunicacao, ItfUsuario pRemetente, ItfGrupoUsuario pDestinatario, String mensagem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
