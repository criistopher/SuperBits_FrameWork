/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.projeto.Jira.Jira;

import com.super_bits.projeto.Jira.Jira.tempo.old.PlanosDeTrabalhoTempoJira;
import com.super_bits.projeto.Jira.UtilSBCoreJira;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class TarefaJira {

    private UtilSBCoreJira.TIPOS_DE_TAREFA_JIRA tipoTarefa;
    private String nomeTarefa;
    private String descricaoTarefa;
    private String codigoTarefa;
    private String tempoEsperado;

    private List<PlanosDeTrabalhoTempoJira> planosDeTrabalho;

    public UtilSBCoreJira.TIPOS_DE_TAREFA_JIRA getTipoTarefa() {
        return tipoTarefa;
    }

    public void setTipoTarefa(UtilSBCoreJira.TIPOS_DE_TAREFA_JIRA tipoTarefa) {
        this.tipoTarefa = tipoTarefa;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public String getDescricaoTarefa() {
        return descricaoTarefa;
    }

    public void setDescricaoTarefa(String descricaoTarefa) {
        this.descricaoTarefa = descricaoTarefa;
    }

    public String getCodigoTarefa() {
        return codigoTarefa;
    }

    public void setCodigoTarefa(String codigoTarefa) {
        this.codigoTarefa = codigoTarefa;
    }

    public String getTempoEsperado() {
        return tempoEsperado;
    }

    public void setTempoEsperado(String tempoEsperado) {
        this.tempoEsperado = tempoEsperado;
    }

    public List<PlanosDeTrabalhoTempoJira> getPlanosDeTrabalho() {
        return planosDeTrabalho;
    }

    public void setPlanosDeTrabalho(List<PlanosDeTrabalhoTempoJira> planosDeTrabalho) {
        this.planosDeTrabalho = planosDeTrabalho;
    }

}
