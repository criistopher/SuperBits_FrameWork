/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.super_bits.projeto.Jira.grupoDeTarefas.ItfGrupoDeTarefas;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaDeEntidade;

/**
 *
 * @author desenvolvedor
 */
public interface ItfPrevisaoEntidade extends ItfGrupoDeTarefas {

    Class getEntidadeVinculada();

    EstruturaDeEntidade getEstruturaDeEntidade();

    int getHorasTrabalhasdas();

    ItfModuloAcaoSistema getModulo();

}
