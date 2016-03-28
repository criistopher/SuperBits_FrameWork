/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
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
public class AcaoDeEntidade extends AcaoDoSistema implements ItfAcaoEntidade {

    private Class classeRelacionada;

    public AcaoDeEntidade(Class classeRelacionada, FabTipoAcaoSistema pTipoAcao) {
        super(pTipoAcao);
        this.classeRelacionada = classeRelacionada;
    }

    @Override
    public Class getClasseRelacionada() {
        return classeRelacionada;
    }

    @Override
    public void setClasseRelacionada(Class classeRelacionada) {
        this.classeRelacionada = classeRelacionada;
    }

}
