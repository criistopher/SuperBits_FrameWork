/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.config.webPaginas.acoes;

import com.super_bits.modulosSB.SBCore.Controller.ControllerAppAbstratoSBCore;
import com.super_bits.modulosSB.SBCore.Controller.anotacoes.InfoAcesso;

/**
 *
 * @author Salvio
 */
public class AcoesWebPaginasDemo extends ControllerAppAbstratoSBCore {

    @InfoAcesso(nomeAmigavel = "AcaoLiberado")
    public static void acaoAcessoLiberado() {

    }

    @InfoAcesso(nomeAmigavel = "AcaoUsuario")
    public static void acaoUsuario() {

    }

    @InfoAcesso(nomeAmigavel = "AcaoAdmin")
    public static void acaoAdmin() {

    }

}
