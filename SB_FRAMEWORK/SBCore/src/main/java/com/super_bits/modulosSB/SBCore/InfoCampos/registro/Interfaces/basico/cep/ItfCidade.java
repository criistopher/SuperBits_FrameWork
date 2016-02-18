/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep;

import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public interface ItfCidade {

    public int getId();

    public void setId(int pID);

    public String getNome();

    public void setNome(String pNome);

    public ItfUnidadeFederativa getUnidadeFederativa();

    public List<ItfBairro> getBairros();

    public String getEstadoPontoNomeCidade();

    public ItfLocalidade getLocalidade();

    public void setLocalidade(ItfLocalidade pLocalidade);

}
