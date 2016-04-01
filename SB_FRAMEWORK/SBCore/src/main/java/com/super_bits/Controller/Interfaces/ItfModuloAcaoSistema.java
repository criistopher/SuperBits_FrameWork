/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.Interfaces;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
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
public interface ItfModuloAcaoSistema {

    public int getId();

    public String getNome();

    public String getDescricao();

    public List<ItfAcaoDoSistema> getAcoes();

    public void setId(int pID);

    public void setNome(String pNome);

    public void setDescricao(String pDescricao);

}
