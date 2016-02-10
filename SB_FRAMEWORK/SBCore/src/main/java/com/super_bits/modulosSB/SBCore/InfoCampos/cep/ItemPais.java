/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.cep;

import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep.ItfPais;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep.ItfUnidadeFederativa;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class ItemPais extends ItemSimples implements ItfPais {

    @Override
    public void setId(int pID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNome(String pNome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItfUnidadeFederativa> getUnidadesFederativas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
