/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Controller.Interfaces.permissoes;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.GrupoCampos;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public interface ItfAcaoFormulario extends ItfAcaoDoSistema {

    public ItfAcaoDoSistema getAcaoExectarFormulario();

    public List<CaminhoCampoReflexao> getCampos();

    public void setAcaoExectarFormulario(ItfAcaoDoSistema acaoExectarFormulario);

    public void setCampos(List<CaminhoCampoReflexao> campos);

    public String getXhtml();

    public void setXhtml(String pXhtml);

    public List<GrupoCampos> getGruposDeCampos();

}
