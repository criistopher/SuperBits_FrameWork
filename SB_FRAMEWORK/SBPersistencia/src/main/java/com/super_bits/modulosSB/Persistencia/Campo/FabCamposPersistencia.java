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
import static com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos.LOOKUP;
import static com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos.LOOKUPMULTIPLO;
import static com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos.getTipoPadraoByClasse;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author sfurbino
 */
public enum FabCamposPersistencia {

    campoAnotacoesPersistencia;

    /**
     *
     * Retorna um tipo de campo padrão de acordo com a classe.
     *
     * @param pClasse
     * @return
     */
    public static Campo getCampoByAnotacoes(Field campoReflexao) {

        InfoCampo anotacaoInfoCampo = campoReflexao.getAnnotation(InfoCampo.class);
        FabCampos tipoDoCampo;
        Campo sbCampo;
        if (anotacaoInfoCampo != null) {
            tipoDoCampo = anotacaoInfoCampo.tipo();

        } else {
            tipoDoCampo = getTipoPadraoByClasse(campoReflexao.getType());

        }

        ManyToOne muitosParaUm = campoReflexao.getAnnotation(ManyToOne.class);

        if (muitosParaUm != null) {
            tipoDoCampo = LOOKUP;

        }
        OneToMany umParaMuitos = campoReflexao.getAnnotation(OneToMany.class);

        if (umParaMuitos != null) {
            tipoDoCampo = LOOKUPMULTIPLO;

        }

        sbCampo = tipoDoCampo.getRegistro();
        sbCampo.setLabel(campoReflexao.getName());

        // CONFIGURANDO OPÇÕES DE SELEÇÃO
        switch (sbCampo.getTipoCampo()) {
            case LOOKUP:

                if (sbCampo.getListaDeOpcoes() != null) {
                    if (sbCampo.getListaDeOpcoes().isEmpty()) {
                        if (muitosParaUm != null) {
                            try {
                                Class classeOpcoes = muitosParaUm.targetEntity();
                                sbCampo.setListaDeOpcoes(UtilSBPersistencia.getListaTodos(classeOpcoes));
                            } catch (Throwable t) {
                                SBCore.RelatarErro(FabErro.LANCAR_EXCECÃO, "Erro obtendo lista de opções em banco de dados para o  campo" + campoReflexao.getDeclaringClass().getSimpleName() + "." + campoReflexao.getName(), t);
                            }
                        }
                    }
                }

                break;
            case LOOKUPMULTIPLO:
                if (umParaMuitos != null) {
                    Class classeOpcoesMultiplo = umParaMuitos.targetEntity();
                    //            sbCampo.setListaDeOpcoes(UtilSBPersistencia.getListaTodos(classeOpcoesMultiplo));
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
