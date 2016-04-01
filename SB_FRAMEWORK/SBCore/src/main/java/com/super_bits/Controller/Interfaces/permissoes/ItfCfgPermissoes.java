/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.Interfaces.permissoes;

import com.super_bits.Controller.Interfaces.ItfResposta;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.ItensGenericos.basico.GrupoUsuariosDoSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.view.menu.MenuSBFW;
import com.super_bits.view.menu.MenusDaSessao;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * @author Salvio
 */
public interface ItfCfgPermissoes {

    public List<ItfPermissao> configuraPermissoes();

    public List<ItfUsuario> configuraUsuarios();

    public ItfResposta ACAOCRUD(Class pEntidade, String TIPOACAO);

    public Class[] getClassesController();

    public MenusDaSessao definirMenu(ItfGrupoUsuario pGrupo);

    public void atualizarInformacoesDePermissoesDoSistema();

    public Method getMetodoByAcao(ItfAcaoDoSistema acao);

}
