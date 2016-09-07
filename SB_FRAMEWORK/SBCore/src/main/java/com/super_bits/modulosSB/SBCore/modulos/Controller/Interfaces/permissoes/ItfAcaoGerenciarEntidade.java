/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import java.util.List;

/**
 *
 *
 *
 *
 * @author desenvolvedor
 */
public interface ItfAcaoGerenciarEntidade extends ItfAcaoEntidade, ItfAcaoFormulario {

    public List<ItfAcaoSecundaria> getAcoesVinculadas();

    public void setAcoesVinculadas(List<ItfAcaoSecundaria> acoesVinculadas);

}
