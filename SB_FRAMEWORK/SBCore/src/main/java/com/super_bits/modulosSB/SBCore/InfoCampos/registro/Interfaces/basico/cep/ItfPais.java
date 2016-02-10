/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep;

import com.sun.mail.iap.Literal;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public interface ItfPais {

    public int getId();

    public void setId(int pID);

    public int getNome();

    public void setNome(String pNome);

    public List<ItfUnidadeFederativa> getUnidadesFederativas();

}
