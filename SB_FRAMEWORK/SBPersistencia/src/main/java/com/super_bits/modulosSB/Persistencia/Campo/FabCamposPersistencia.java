/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.Persistencia.Campo;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.Campo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.TIPO_DECLARACAO;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import static com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos.LISTA_OBJETOS;
import static com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos.OBJETO_DE_UMA_LISTA;
import javax.persistence.ManyToMany;

/**
 *
 * @author sfurbino
 */
public enum FabCamposPersistencia {

    campoAnotacoesPersistencia;

    public static TIPO_DECLARACAO getTipoDeclaracao(Field campoReflexao) {

        ManyToOne muitosParaUm = campoReflexao.getAnnotation(ManyToOne.class);

        if (muitosParaUm != null) {
            return TIPO_DECLARACAO.MUITOS_PARA_UM;
        }

        OneToMany umParaMuitos = campoReflexao.getAnnotation(OneToMany.class);
        ManyToMany MuitosParaMuitos = campoReflexao.getAnnotation(ManyToMany.class);
        if (umParaMuitos != null || MuitosParaMuitos != null) {
            return TIPO_DECLARACAO.MUITOS_PARA_MUITOS;
        }

        return null;

    }

    /**
     *
     * Retorna um tipo de campo padrão de acordo com a classe.
     *
     * @param campoReflexao
     * @param pClasse
     * @return
     */
    public static Campo getCampoByAnotacoes(Field campoReflexao) {

        InfoCampo anotacaoInfoCampo = campoReflexao.getAnnotation(InfoCampo.class);
        FabCampos tipoDoCampo;

        TIPO_DECLARACAO tipoDeclaracaoPersistencia = getTipoDeclaracao(campoReflexao);

        Campo sbCampo;
        sbCampo = FabCampos.getCampoByAnotacoesSimplesSemPersistencia(campoReflexao);

        if (tipoDeclaracaoPersistencia != null) {
            sbCampo.setTipoDeclaracao(tipoDeclaracaoPersistencia);

            switch (tipoDeclaracaoPersistencia) {
                case MUITOS_PARA_MUITOS:
                    sbCampo.setTipoCampo(FabCampos.LISTA_OBJETOS);
                    break;
                case MUITOS_PARA_MUITOS_COM_REPETICAO:
                    sbCampo.setTipoCampo(LISTA_OBJETOS);
                    break;
                case MUITOS_PARA_UM:
                    sbCampo.setTipoCampo(OBJETO_DE_UMA_LISTA);
                    break;

                default:
                    throw new AssertionError(tipoDeclaracaoPersistencia.name());

            }
        }

        // CONFIGURANDO OPÇÕES DE SELEÇÃO
        switch (sbCampo.getTipoDeclaracao()) {
            case SIMPLES:
                break;
            case VALOR_CALCULADO:
                break;
            case LISTA_DINIMICA:
                break;
            case MUITOS_PARA_MUITOS:
                break;
            case MUITOS_PARA_MUITOS_COM_REPETICAO:
                break;
            case MUITOS_PARA_UM:
                break;
            case OBJETO_EMBUTIDO:
                break;
            case CAMPO_TRANSIENTE:
                break;
            case OBJETO_TRANSIENTE:
                break;
            default:
                throw new AssertionError(sbCampo.getTipoDeclaracao().name());

        }

        switch (sbCampo.getTipoCampo()) {
            case OBJETO_DE_UMA_LISTA:

                if (sbCampo.getListaDeOpcoes() == null) {
                    ManyToOne muitosParaUm = campoReflexao.getAnnotation(ManyToOne.class);
                    if (muitosParaUm != null) {
                        try {
                            Class classeOpcoes = muitosParaUm.targetEntity();
                            if (classeOpcoes.equals(void.class)) {
                                throw new UnsupportedOperationException("o target entity não foi definodo na anotação @manyToOne do campo" + sbCampo.getNome() + " em" + campoReflexao.getDeclaringClass().getTypeName());
                            }
                            sbCampo.setListaDeOpcoes(UtilSBPersistencia.getListaTodos(classeOpcoes));
                        } catch (Throwable t) {
                            SBCore.RelatarErro(FabErro.LANCAR_EXCECÃO, "Erro obtendo lista de opções em banco de dados para o  campo" + campoReflexao.getDeclaringClass().getSimpleName() + "." + campoReflexao.getName(), t);
                        }
                    }

                }

                break;
            case LISTA_OBJETOS:
                OneToMany umParaMuitos = campoReflexao.getAnnotation(OneToMany.class);
                if (umParaMuitos != null) {
                    Class classeOpcoesMultiplo = umParaMuitos.targetEntity();
                    //  sbCampo.setListaDeOpcoes(UtilSBPersistencia.getListaTodos(classeOpcoesMultiplo));
                }
                break;
        }

        if (anotacaoInfoCampo != null) {
            if (anotacaoInfoCampo.Mask().length() > 0) {
                sbCampo.setMascara(anotacaoInfoCampo.Mask());
            }
            if (anotacaoInfoCampo.label().length() > 0) {
                sbCampo.setLabel(anotacaoInfoCampo.label());
            }
            sbCampo.setObrigatorio(anotacaoInfoCampo.obrigatorio());
            if (anotacaoInfoCampo.valoresAceitos().length > 0) {
                anotacaoInfoCampo.valoresAceitos();
            }
        }

        Annotation[] outrasAnotacoes = campoReflexao.getAnnotations();

        NotNull nulo = campoReflexao.getAnnotation(NotNull.class);
        if (nulo != null) {
            sbCampo.setObrigatorio(true);
        }

        for (Annotation anotacao : outrasAnotacoes) {
            // Verificar outras anotacoes
        }

        return sbCampo;
    }

}
