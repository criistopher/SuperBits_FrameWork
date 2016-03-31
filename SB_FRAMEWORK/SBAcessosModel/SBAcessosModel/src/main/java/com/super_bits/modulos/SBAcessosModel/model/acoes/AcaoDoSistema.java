/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author desenvolvedor
 */
@Entity
public class AcaoDoSistema extends EntidadeSimples implements ItfAcaoDoSistema {

    private FabTipoAcaoSistema tipoAcao;
    protected FabTipoAcaoSistemaGenerica acaoGenerica;
    @Id
    private int id;
    private String nomeAcao;
    private String iconeAcao;
    private String cor;
    private String descricao;
    private boolean precisaPermissao;
    private ModuloAcaoSistema modulo;
    private String idDescritivoJira;

    public AcaoDoSistema() {
        System.out.println("ATENÇÃO UMA AÇÃO DO SISTEMA SEM PARAMETROS NO CONSTRUTOR SÓ DEVE SER INSTANCIADA PELO HIBERNATE");
    }

    public AcaoDoSistema(FabTipoAcaoSistema ptipoAcao, ItfFabricaAcoes pAcao) {

        tipoAcao = ptipoAcao;
        nomeAcao = pAcao.toString();
        descricao = "Descrição não documentada";

    }

    public void copiarDadosDaAcao(ItfAcaoDoSistema pAcaoOriginal) {

    }

    public boolean isUmaAcaoGenerica() {
        return acaoGenerica != null;
    }

    public FabTipoAcaoSistema getTipoAcao() {
        return tipoAcao;
    }

    @Override
    public String getNomeAcao() {
        return nomeAcao;
    }

    @Override
    public String getIconeAcao() {
        return iconeAcao;

    }

    @Override
    public String getCor() {
        return cor;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean isPrecisaPermissao() {
        return precisaPermissao;
    }

    @Override
    public void setId(int pId) {
        id = pId;
    }

    @Override
    public void setIcone(String pIcone) {
        iconeAcao = pIcone;
    }

    @Override
    public void setModuloAcaoSistema(ItfModuloAcaoSistema pmodulo) {
        modulo = (ModuloAcaoSistema) pmodulo;
    }

    @Override
    public boolean isConfigurado() {

        // verifica se o nome da ação foi alterado, ou se o icone foi setado
        return !(null == iconeAcao || !nomeAcao.equals(tipoAcao.toString()));

    }

    @Override
    public String getNomeEnumOriginal() {
        return tipoAcao.toString();
    }

    @Override
    public FabTipoAcaoSistema getTipoAcaoSistema() {
        return getTipoAcao();
    }

    @Override
    public boolean isTemAcaoPrincipal() {
        return this.getClass().isAssignableFrom(ItfAcaoSecundaria.class);

    }

    @Override
    public ModuloAcaoSistema getModulo() {
        return modulo;
    }

    public void setModulo(ModuloAcaoSistema modulo) {
        this.modulo = modulo;
    }

    public void setTipoAcao(FabTipoAcaoSistema tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

    public void setAcaoGenerica(FabTipoAcaoSistemaGenerica acaoGenerica) {
        this.acaoGenerica = acaoGenerica;
    }

    public void setNomeAcao(String nomeAcao) {
        this.nomeAcao = nomeAcao;
    }

    public void setIconeAcao(String iconeAcao) {
        this.iconeAcao = iconeAcao;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPrecisaPermissao(boolean precisaPermissao) {
        this.precisaPermissao = precisaPermissao;
    }

    public String getIdDescritivoJira() {
        return idDescritivoJira;
    }

    public void setIdDescritivoJira(String idDescritivoJira) {
        this.idDescritivoJira = idDescritivoJira;
    }

}
