/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.fabrica;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.acoes.AcaoDeEntidade;
import com.super_bits.Controller.acoes.AcaoDeEntidadeController;
import com.super_bits.Controller.acoes.acaoDeEntidade.AcaoFormularioDeEntidade;
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
     * Não implementado ainda;
     *
     * @return
     */
    public List<ItfGrupoUsuario> getAcessoGruposLiberadosPadrao();

    /**
     *
     * Retorna o mesmo que o getRegistro
     *
     * @return
     */
    public ItfAcaoDoSistema getAcaoDoSistema();

    public AcaoDeEntidade getAcaoDeEntidade();

    public AcaoFormularioDeEntidade getAcaoEntidadeFormulario(ItfAcaoDoSistema acaoPrincipal, Class classeRelacionada, String pXhtml);

    public AcaoDeEntidadeController getAcaoEntidadeController();

}
