/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;
import java.lang.reflect.Method;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author sfurbino
 */
@Entity
public class AcaoDoSistema extends EntidadeSimples implements ItfAcaoDoSistema {

    @Id
    @GeneratedValue()
    private int id;

    @InfoCampo(tipo = FabCampos.AAA_NOME_CURTO)
    private String nomeAcao;

    private String iconeAcao;

    private String cor;
    @InfoCampo(tipo = FabCampos.AAA_DESCRITIVO)
    private String descricao;

    private Method metodo;

    private String urlAction;

    public AcaoDoSistema() {
        super();
    }

    public AcaoDoSistema(int id, String nomeAcao, String iconeAcao, String cor, String descricao, Method metodo, String pURLAction) {
        this.id = id;
        this.nomeAcao = nomeAcao;
        this.iconeAcao = iconeAcao;
        this.cor = cor;
        this.descricao = descricao;
        this.metodo = metodo;
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

    /**
     *
     * @see ItfAcaoDoSistema#getMetodoExecucao()
     *
     * @return
     */
    @Override
    public Method getMetodoExecucao() {
        return metodo;
    }

    @Override
    public String getUrlAction() {
        return urlAction;
    }

}
