/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.Persistencia.dao;

import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author sfurbino
 */
public class InfoPerisistirEntidade {

    private Object pEntidade;
    private List<Object> pEntidades;
    private EntityManager pEM;
    private FabInfoPersistirEntidade tipoAlteracao;

    private enum TIPO_REGISTRO {

        UNICO, MULTIPLOS, NULO
    }

    public InfoPerisistirEntidade(Object pEntidade, List<Object> pEntidades, EntityManager pEM, FabInfoPersistirEntidade tipoAlteracao) {
        this.pEntidade = pEntidade;
        this.pEntidades = pEntidades;
        this.pEM = pEM;
        this.tipoAlteracao = tipoAlteracao;
    }

    private String getValoresRegistro() {
        String resposta = "";

        try {
            switch (getTipoRegistro()) {

                case UNICO:
                    ItfBeanSimples entidadeUnica = (ItfBeanSimples) pEntidade;
                    resposta += "nome:" + entidadeUnica.getNomeCurto() + " id:" + entidadeUnica.getId();
                    break;
                case MULTIPLOS:
                    ItfBeanSimples entidade = (ItfBeanSimples) pEntidades.get(0);
                    resposta += "nome:" + entidade.getNomeCurto() + "id:" + entidade.getId();
                    break;
                case NULO:
                    return "registro nulo";

            }
        } catch (Throwable erro) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro em tentativa de obter id e nomeCurto do Objeto", erro);
            resposta = "Registro sem possibilidade de leitura";
        }
        return resposta;

    }

    private TIPO_REGISTRO getTipoRegistro() {
        if (pEntidade != null) {
            return TIPO_REGISTRO.UNICO;

        }
        if (pEntidade != null) {
            if (pEntidades.size() > 0) {
                return TIPO_REGISTRO.MULTIPLOS;
            }
        }
        return TIPO_REGISTRO.NULO;
    }

    private String entidadesEnviadas() {
        String resposta = "Registro nulo";
        if (pEntidade != null) {
            resposta = pEntidade.getClass().getName();

        }
        if (pEntidades != null) {
            if (pEntidades.size() > 0) {
                resposta = pEntidades.get(0).getClass().getName();
            }
        }
        return resposta;

    }

    public InfoPerisistirEntidade(FabInfoPersistirEntidade pTipoAlteracao) {
        tipoAlteracao = pTipoAlteracao;
    }

    @Override
    public String toString() {
        String relatorio = "Informações sobre o InfoPersistencia Entidade: /n" + "Entidade" + entidadesEnviadas()
                + getValoresRegistro();
        return relatorio;
    }

    public Object getpEntidade() {
        return pEntidade;
    }

    public void setpEntidade(Object pEntidade) {
        this.pEntidade = pEntidade;
    }

    public List<Object> getpEntidades() {
        return pEntidades;
    }

    public void setpEntidades(List<Object> pEntidades) {
        this.pEntidades = pEntidades;
    }

    public EntityManager getpEM() {
        return pEM;
    }

    public void setpEM(EntityManager pEM) {
        this.pEM = pEM;
    }

    public FabInfoPersistirEntidade getTipoAlteracao() {
        return tipoAlteracao;
    }

    public void setTipoAlteracao(FabInfoPersistirEntidade tipoAlteracao) {
        this.tipoAlteracao = tipoAlteracao;
    }

}
