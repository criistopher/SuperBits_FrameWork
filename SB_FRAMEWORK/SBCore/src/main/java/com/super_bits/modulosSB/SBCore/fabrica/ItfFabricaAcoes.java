/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.fabrica;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfGrupoUsuario;
import java.util.List;

/**
 *
 * Interface para implementar fabricas de Modulos
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 16/12/2015
 * @version 1.0
 */
public interface ItfFabricaAcoes extends ItfFabrica {

    /**
     *
     * Retorna o mesmo que o getRegistro
     *
     * @return
     */
    public ItfAcaoDoSistema getAcaoDoSistema();

    public ItfAcaoEntidade getAcaoDeEntidade();

    public ItfAcaoFormularioEntidade getAcaoEntidadeFormulario();

    public ItfAcaoControllerEntidade getAcaoEntidadeController();

    public ItfAcaoController getAcaoController();

    public ItfAcaoGerenciarEntidade geAcaoGerenciarEntidade();

    public Class getDominio();

    public String getNomeModulo();

}
