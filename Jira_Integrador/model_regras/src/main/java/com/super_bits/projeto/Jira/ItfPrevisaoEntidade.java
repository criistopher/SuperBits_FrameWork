/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.EstruturaDeEntidade;
import com.super_bits.projeto.Jira.Jira.TarefaSuperBits;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public interface ItfPrevisaoEntidade {

    CustosDesenvolvimento getCustoDesenvolvimento();

    Class getEntidadeVinculada();

    EstruturaDeEntidade getEstruturaDeEntidade();

    int getHorasTrabalhasdas();

    ItfModuloAcaoSistema getModulo();

    PrevisaoProjeto getPrevisaoProjeto();

    List<TarefaSuperBits> getTarefasVinculadas();

}
