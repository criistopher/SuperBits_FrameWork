/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import java.lang.reflect.Method;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author sfurbino
 */
@Entity
public class AcaoDoSistema extends EntidadeSimples implements ItfAcaoDoSistema {

    @Id
    private int id;

    @InfoCampo(tipo = FabCampos.AAA_NOME_CURTO)
    private String nomeAcao;

    private String iconeAcao;

    private String cor;
    @InfoCampo(tipo = FabCampos.AAA_DESCRITIVO)
    private String descricao;

    private boolean precisaPermissao;

    private String urlAction;

    private int idMetodo;

    @ManyToOne
    private ModuloAcaoSistema modulo;

    public AcaoDoSistema() {
        super();
    }

    public AcaoDoSistema(int id, String nomeAcao, String iconeAcao, String cor, String descricao, Method metodo, String pURLAction) {
        this.id = id;
        this.nomeAcao = nomeAcao;
        this.iconeAcao = iconeAcao;
        this.cor = cor;
        this.descricao = descricao;

        this.urlAction = pURLAction;
    }

    /**
     *
     * @see ItfAcaoDoSistema#getNomeAcao()
     *
     * @return
     */
    @Override
    public String getNomeAcao() {
        return nomeAcao;
    }

    /**
     *
     * @see ItfAcaoDoSistema#getIconeAcao()
     * @return
     */
    @Override
    public String getIconeAcao() {
        return iconeAcao;
    }

    /**
     * @see ItfAcaoDoSistema#getCor()
     *
     * @return
     */
    @Override
    public String getCor() {
        return cor;
    }

    /**
     *
     * @see ItfAcaoDoSistema#getDescricao()
     *
     *
     * @return
     */
    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean isPrecisaPermissao() {
        return precisaPermissao;
    }

    public void setPrecisaPermissao(boolean precisaPermissao) {
        this.precisaPermissao = precisaPermissao;
    }

    @Override
    public String getUrlAction() {
        return urlAction;
    }

    public void setUrlAction(String urlAction) {
        this.urlAction = urlAction;
    }

    @Override
    public int getIdMetodo() {
        return idMetodo;
    }

    /**
     *
     * @param idMetodo
     */
    @Override
    public void setIdMetodo(int idMetodo) {
        this.idMetodo = idMetodo;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public ItfModuloAcaoSistema getModulo() {
        return modulo;
    }

    @Override
    public void setModuloAcaoSistema(ItfModuloAcaoSistema pmodulo) {
        modulo = (ModuloAcaoSistema) pmodulo;
    }

}
