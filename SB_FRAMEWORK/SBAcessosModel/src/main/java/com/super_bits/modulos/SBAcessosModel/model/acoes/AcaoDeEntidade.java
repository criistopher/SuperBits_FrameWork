/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;
import javax.persistence.Entity;
import javax.persistence.Transient;

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
@Entity
@InfoClasse(tags = "Ação relacionada a uma entidade", plural = "Ações de entidade")
public class AcaoDeEntidade extends AcaoDoSistema implements ItfAcaoEntidade {

    @Transient
    private Class classeRelacionada;

    public AcaoDeEntidade() {

    }

    public AcaoDeEntidade(Class classeRelacionada, FabTipoAcaoSistema pTipoAcao, ItfFabricaAcoes pFabricaAcao) {
        super(pTipoAcao, pFabricaAcao);
        this.classeRelacionada = classeRelacionada;
    }

    public AcaoDeEntidade(Class classeRelacionada, FabTipoAcaoSistema pTipoAcao, ItfFabricaAcoes pFabricaAcao, FabTipoAcaoSistemaGenerica pFabricaAcaoGenerica) {
        super(pTipoAcao, pFabricaAcao, pFabricaAcaoGenerica);
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

    @Override
    public FabTipoAcaoSistemaGenerica getTipoAcaoGenerica() {
        return tipoAcaoGenerica;
    }

    @Override
    public FabTipoAcaoSistema getTipoAcaoSistema() {
        return super.getTipoAcaoSistema(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoGerenciarEntidade getAcaoDeGestaoEntidade() {
        return getComoSecundaria().getAcaoPrincipal();
    }

}
