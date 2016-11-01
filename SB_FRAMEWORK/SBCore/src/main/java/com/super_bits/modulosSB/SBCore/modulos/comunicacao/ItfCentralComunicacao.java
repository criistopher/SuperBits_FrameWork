/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

/**
 *
 * @author salvioF
 */
public interface ItfCentralComunicacao {

    public FabTipoRespostaComunicacao iniciarComunicacaoSistema_Usuairo(FabTipoInicioComunicacao tipocomunicacao, String mensagem);

    public FabTipoRespostaComunicacao iniciarComunicacaoEntre_Usuairos(FabTipoInicioComunicacao tipocomunicacao, ItfUsuario pRemetente, ItfUsuario pDestinatario, String mensagem);

    public FabTipoRespostaComunicacao iniciarComunicacaoUsuairo_paraGrupo(FabTipoInicioComunicacao tipocomunicacao, ItfUsuario pRemetente, ItfGrupoUsuario pDestinatario, String mensagem);

}
