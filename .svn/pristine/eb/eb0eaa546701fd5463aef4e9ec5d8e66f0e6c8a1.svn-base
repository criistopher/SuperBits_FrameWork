/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.Controller.anotacoes;

import com.super_bits.modulosSB.SBCore.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;
import java.lang.reflect.Method;

/**
 *
 * @author sfurbino
 */
public class AcaoDoSistema extends ItemSimples implements ItfAcaoDoSistema {

    @InfoCampo(tipo = FabCampos.ID)
    private final int id;

    @InfoCampo(tipo = FabCampos.AAA_NOME_CURTO)
    private final String nomeAcao;

    private final String iconeAcao;

    private final String cor;
    @InfoCampo(tipo = FabCampos.AAA_DESCRITIVO)
    private final String descricao;

    private final Method metodo;

    private String urlAction;

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
