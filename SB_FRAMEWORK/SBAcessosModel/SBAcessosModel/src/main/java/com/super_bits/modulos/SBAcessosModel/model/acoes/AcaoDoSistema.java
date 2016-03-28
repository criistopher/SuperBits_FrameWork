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

/**
 *
 * @author desenvolvedor
 */
public class AcaoDoSistema extends EntidadeSimples implements ItfAcaoDoSistema {

    private final FabTipoAcaoSistema tipoAcao;
    protected FabTipoAcaoSistemaGenerica acaoGenerica;
    private int id;
    private String nomeAcao;
    private String iconeAcao;
    private String cor;
    private String descricao;
    private boolean precisaPermissao;
    private ModuloAcaoSistema modulo;

    public void copiarDadosDaAcao(ItfAcaoDoSistema pAcaoOriginal) {

    }

    public boolean isUmaAcaoGenerica() {
        return acaoGenerica != null;
    }

    public AcaoDoSistema(FabTipoAcaoSistema pTipoAcao) {
        tipoAcao = pTipoAcao;
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

}
