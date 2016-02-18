/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep;

import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public interface ItfUnidadeFederativa {

    public int getId();

    public void setId(int pId);

    public String getNome();

    public void setNome(String pNome);

    public List<ItfCidade> getCidades();

}
