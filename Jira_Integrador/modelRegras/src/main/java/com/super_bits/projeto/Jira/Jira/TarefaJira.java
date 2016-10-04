/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.projeto.Jira.Jira;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.BasicPriority;
import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.IssueFieldId;
import com.atlassian.jira.rest.client.api.domain.IssueType;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.jira.rest.client.api.domain.input.ComplexIssueInputFieldValue;
import com.atlassian.jira.rest.client.api.domain.input.FieldInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.projeto.Jira.requisito.ItfRequisitoDoSistema;
import com.super_bits.projeto.Jira.Jira.tempo.old.PlanosDeTrabalhoTempoJira;
import com.super_bits.projeto.Jira.UtilSBCoreJira;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author desenvolvedor
 */
public class TarefaJira {

    public enum TIPO_ORIGEM_TAREFA {
        BANCO_DE_DADOS, ACAO_DO_SISTEMA, REQUISITO;
    }

    private boolean gropoTarefas;
    private TIPO_ORIGEM_TAREFA tipoOrigem;
    private UtilSBCoreJira.TIPOS_DE_TAREFA_JIRA tipoTarefa;
    private String nomeTarefa;
    private String descricaoTarefa;
    private String codigoTarefa;
    private String tempoEsperado;
    private ItfAcaoDoSistema acaoVinculada;
    private Class tabelaVinculada;
    private UtilSBCoreJira.TIPO_GRUPO_TAREFA tipoGrupoTarefa;
    private ItfRequisitoDoSistema requisitoVinculado;

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
        String retornoDescricao = descricaoTarefa;

        if (tipoOrigem == TIPO_ORIGEM_TAREFA.ACAO_DO_SISTEMA) {
            if (!isGropoTarefas() & !acaoVinculada.isUmaAcaoGestaoDominio()) {
                try {
                    if (acaoVinculada.isUmaAcaoFormulario()) {
                        if (!acaoVinculada.getComoFormulario().getCampos().isEmpty()) {
                            retornoDescricao += "\n Este formulário deve exibir os seguintes campos <br>";
                            for (CaminhoCampoReflexao campo : acaoVinculada.getComoFormulario().getCampos()) {
                                retornoDescricao += " \n" + campo.getCaminhoCompletoString();
                            }
                        }
                    }
                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando campos na ação " + nomeTarefa, t);
                }
            }
            if (acaoVinculada.getIdDescritivoJira() != null) {

                if (acaoVinculada.getIdDescritivoJira().length() > 2) {
                    retornoDescricao += " \n conforme especificado em " + acaoVinculada.getIdDescritivoJira();
                }

            }
        }

        return retornoDescricao;
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

    public ItfAcaoDoSistema getAcaoVinculada() {
        return acaoVinculada;
    }

    public void setAcaoVinculada(ItfAcaoDoSistema acaoVinculada) {
        if (tipoOrigem != null) {
            throw new UnsupportedOperationException("A origem da ação já foi definida como" + tipoOrigem);
        }
        tipoOrigem = TIPO_ORIGEM_TAREFA.ACAO_DO_SISTEMA;

        this.acaoVinculada = acaoVinculada;
    }

    public Class getTabelaVinculada() {
        return tabelaVinculada;
    }

    public void setTabelaVinculada(Class tabelaVinculada) {
        if (tipoOrigem != null) {
            throw new UnsupportedOperationException("A origem da ação já foi definida");
        }
        tipoOrigem = TIPO_ORIGEM_TAREFA.BANCO_DE_DADOS;

        this.tabelaVinculada = tabelaVinculada;
    }

    public void setRequisitoVinculado(ItfRequisitoDoSistema pRequisitoVinculado) {
        if (tipoOrigem != null) {
            throw new UnsupportedOperationException("A origem da ação já foi definida como" + tipoOrigem);
        }
        tipoOrigem = TIPO_ORIGEM_TAREFA.REQUISITO;
        this.requisitoVinculado = pRequisitoVinculado;
    }

    public IssueInput getIssue(BasicProject pProjeto, IssueType pTipoIssue, BasicPriority prioridade, BasicIssue acaoPrincipal, User pUsuario) {

        IssueInputBuilder inputBuilder = new IssueInputBuilder(pProjeto, pTipoIssue);

        Map<String, Object> map = new HashMap<>();
        map.put("originalEstimate", getTempoEsperado());
        inputBuilder.setFieldInput(new FieldInput(IssueFieldId.TIMETRACKING_FIELD, new ComplexIssueInputFieldValue(map)));

        Map<String, Object> parent = new HashMap<String, Object>();
        parent.put("key", acaoPrincipal.getKey());
        inputBuilder.setFieldInput(new FieldInput("parent", new ComplexIssueInputFieldValue(parent)));
        inputBuilder.setFieldValue("labels", Arrays.asList(getReferencia()));
        inputBuilder.setSummary(getNomeTarefa());
        inputBuilder.setDescription(getDescricaoTarefa());
        inputBuilder.setPriority(prioridade);
        if (pUsuario != null) {
            inputBuilder.setAssignee(pUsuario);
        }
        return inputBuilder.build();

    }

    public IssueInput getIssueGrupoAcoes(BasicProject pProjeto, IssueType pTipoIssue, BasicPriority prioridade, User pUsuarioResponsavel) {

        IssueInputBuilder inputBuilder = new IssueInputBuilder(pProjeto, pTipoIssue);

        Map<String, Object> map = new HashMap<>();
        map.put("originalEstimate", getTempoEsperado());

        inputBuilder.setFieldValue("labels", Arrays.asList(getReferencia()));

        //inputBuilder.setFieldInput(new FieldInput(IssueFieldId. , new ComplexIssueInputFieldValue(map)));
        inputBuilder.setSummary(getNomeTarefa());
        inputBuilder.setDescription(getDescricaoTarefa());
        inputBuilder.setPriority(prioridade);

        if (pUsuarioResponsavel != null) {
            inputBuilder.setAssignee(pUsuarioResponsavel);
        }
        return inputBuilder.build();

    }

    public String getReferencia() {
        try {
            String strInicioReferencia = "ref[@";
            String fimReferencia = "]";
            if (isGropoTarefas()) {
                strInicioReferencia = "grp-";

                switch (tipoGrupoTarefa) {
                    case TELA_GESTAO_ENTIDADE:
                        strInicioReferencia += "GT";
                        break;
                    case MODULO_CONTROLLER:
                        strInicioReferencia += "MD";
                        break;
                    case MODELAGEM_TABELA:
                        strInicioReferencia += "TM";
                        break;
                    case ACAO_BANCO_AMBIENTE_E_ADEQUACAO:
                        strInicioReferencia += "TA";
                        break;
                    case REQUISITO_PERSONALIZADAO:
                        strInicioReferencia += "RP";
                        break;
                    default:
                        throw new AssertionError(tipoGrupoTarefa.name());

                }
            } else {

                strInicioReferencia += tipoTarefa.getSigla();

            }

            switch (tipoOrigem) {

                case BANCO_DE_DADOS:
                    return strInicioReferencia + tabelaVinculada.getSimpleName() + fimReferencia;

                case ACAO_DO_SISTEMA:
                    return strInicioReferencia + acaoVinculada.getNomeUnico() + fimReferencia;
                default:
                    throw new AssertionError(tipoOrigem.name());

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro obtendo refenrencia da tarefa" + nomeTarefa, t);
            return null;
        }

    }

    public boolean isGropoTarefas() {
        return gropoTarefas;
    }

    public void setGropoTarefas(boolean gropoTarefas) {
        this.gropoTarefas = gropoTarefas;
    }

    public TIPO_ORIGEM_TAREFA getTipoOrigem() {
        return tipoOrigem;
    }

    public UtilSBCoreJira.TIPO_GRUPO_TAREFA getTipoGrupoTarefa() {
        return tipoGrupoTarefa;
    }

    public void setTipoGrupoTarefa(UtilSBCoreJira.TIPO_GRUPO_TAREFA tipoGrupoTarefa) {
        this.tipoGrupoTarefa = tipoGrupoTarefa;
    }

    public ItfRequisitoDoSistema getRequisitoVinculado() {
        return requisitoVinculado;
    }

    public String getNomeUnicoTarefa() {
        switch (tipoOrigem) {
            case BANCO_DE_DADOS:
                return getTabelaVinculada().getSimpleName();
            case ACAO_DO_SISTEMA:
                return getAcaoVinculada().getNomeUnico();
            case REQUISITO:
                return String.valueOf("Trf-requisito" + getRequisitoVinculado().getId() + getTipoTarefa().getSigla());

        }
        return null;
    }

}
