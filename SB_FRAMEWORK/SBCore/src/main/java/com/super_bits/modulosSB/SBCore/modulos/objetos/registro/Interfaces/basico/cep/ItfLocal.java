/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;

/**
 *
 *
 * Define um local especifico do planeta.
 *
 *
 * @author desenvolvedor
 */
public interface ItfLocal extends ItfBeanSimples {

    public long getLongitude();

    public long getLatitude();

    public void setLatitude(long pLatitude);

    public void setLongitude(long pLongitude);

    public ItfBairro getBairro();

    public void setBairro(ItfBairro bairro);

    public String getNome();

    public void setNome(String pNome);

    public String getComplemento();

    public void setComplemento(String pComplemento);

}
