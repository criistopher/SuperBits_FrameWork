/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.acoes;

import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;

/**
 *
 * Uma ação de entidade é uma ação relacionada a alguma entidade, em geral a
 * entidade determina o dominio da ação ou seja, o endereço onde esta ação
 * ficará disponível
 *
 * Ex: http://minhaAplicacao.com.br/Entidade/acaoDaEntidade
 *
 * Todas as ações que são relativas a alteração de uma entidade específica devem
 * extender esta ação.
 *
 * -Mas existem outras ações de entidade que extendem esta classe, como ação de
 * entidade gerenciamento, formularioDeEdicao etc.
 *
 * @author desenvolvedor
 */
public abstract class AcaoDeEntidade extends AcaoDoSistema {

    private final Class classeRelacionada;

    public AcaoDeEntidade(Class classeRelacionada, FabTipoAcaoSistema pTipoAcao) {
        super(pTipoAcao);
        this.classeRelacionada = classeRelacionada;
    }

}
