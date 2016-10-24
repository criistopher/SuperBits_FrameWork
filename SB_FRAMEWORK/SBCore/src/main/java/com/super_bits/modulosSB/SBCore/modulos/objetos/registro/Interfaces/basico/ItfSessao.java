/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfPermissao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.sessao.ItfTipoView;
import java.util.Date;
import java.util.List;

/**
 *
 * Interface que representa uma sesao de usuário
 *
 * @author Salvio
 */
public interface ItfSessao {

    public ItfUsuario getUsuario();

    public Date getInicio();

    public Date getFim();

    public List<ItfPermissao> getAcoesRealizadas();

    public void setUsuario(ItfUsuario pUsuario);

    public boolean isIdentificado();

    public ItfTipoView getTipoView();

    public boolean isTipoViewDefinido();

}