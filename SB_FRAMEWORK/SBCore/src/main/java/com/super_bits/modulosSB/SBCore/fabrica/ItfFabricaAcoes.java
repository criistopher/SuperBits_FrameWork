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
     * @return Uma ação do sistema
     */
    public ItfAcaoDoSistema getAcaoDoSistema();

    /**
     *
     * @return Uma ação vinculada a uma classe de Entidade específica
     */
    public ItfAcaoEntidade getAcaoDeEntidade();

    /**
     *
     * @return Uma ação referente a coleta de dados (ação de formulário)
     */
    public ItfAcaoFormularioEntidade getAcaoEntidadeFormulario();

    /**
     *
     * Atenção: retorna erro caso a ação não seja deste tipo
     *
     * @return Uma ação do tipo Controller (que gera alteração no sistema)
     * Contendo a Entidade Vinculada a ação
     *
     *
     */
    public ItfAcaoControllerEntidade getAcaoEntidadeController();

    /**
     * Atenção: retorna erro caso a ação não seja deste tipo
     *
     * @return Uma ação do tipo Controller (que gera alteração no sistema)
     */
    public ItfAcaoController getAcaoController();

    /**
     * Atenção: retorna erro caso a ação não seja deste tipo
     *
     * @return A ação de gestão de entidade,
     */
    public ItfAcaoGerenciarEntidade geAcaoGerenciarEntidade();

    /**
     *
     * Retorna a Claase de entidade que a ação está vinculada,Depreciado, pois
     * será substituido por Anotacoes @InfoAcao
     *
     *
     *
     * @return A Classe referente a Entidade principal do Domínio
     */
    @Deprecated
    public Class getEntidadeDominio();

    /**
     *
     * @return Nome do módulo vinculado a ação
     */
    @Deprecated
    public String getNomeModulo();

}
