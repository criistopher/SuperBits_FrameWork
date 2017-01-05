/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.MenusDaSessao;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * @author Salvio
 */
public interface ItfCfgPermissoes {

    public List<ItfPermissao> configuraPermissoes();

    public List<ItfUsuario> configuraUsuarios();

    public Class[] getClassesController();

    public MenusDaSessao definirMenu(ItfGrupoUsuario pGrupo);

    public void atualizarInformacoesDePermissoesDoSistema();

    public Method getMetodoByAcao(ItfAcaoDoSistema acao);

    public boolean isAcaoPermitidaUsuarioLogado(ItfAcaoDoSistema acao);

    public boolean isAcaoPermitidaUsuario(ItfUsuario pUsuario, ItfAcaoDoSistema acao);

    public boolean isPermitidoUsuario(ItfUsuario pUsuario, ItfPermissao pPermissao);

}
