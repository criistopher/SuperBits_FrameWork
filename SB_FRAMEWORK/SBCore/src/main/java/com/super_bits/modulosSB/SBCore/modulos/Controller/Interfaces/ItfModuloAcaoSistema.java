/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.util.List;

/**
 *
 *
 *
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 19/12/2015
 * @version 1.0
 */
public interface ItfModuloAcaoSistema extends ItfBeanSimples {

    @Override
    public int getId();

    @Override
    public String getNome();

    public String getDescricao();

    public List<ItfAcaoDoSistema> getAcoes();

    @Override
    public void setId(int pID);

    @Override
    public void setNome(String pNome);

    public void setDescricao(String pDescricao);

    public List<ItfAcaoDoSistema> getAcoesGestaoMB();

    public boolean isUmModuloNativo();

}
