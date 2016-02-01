/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.Persistencia.Campo;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.Campo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import static com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos.LOOKUP;
import static com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos.LOOKUPMULTIPLO;
import static com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos.getTipoPadraoByClasse;
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
    public static Campo getCampoByAnotacoes(Field campo) {

        InfoCampo anotacaoInfoCampo = campo.getAnnotation(InfoCampo.class);
        FabCampos tipoDoCampo;
        Campo sbCampo;
        if (anotacaoInfoCampo != null) {
            tipoDoCampo = anotacaoInfoCampo.tipo();

        } else {
            tipoDoCampo = getTipoPadraoByClasse(campo.getType());

        }

        ManyToOne muitosParaUm = campo.getAnnotation(ManyToOne.class);

        if (muitosParaUm != null) {
            tipoDoCampo = LOOKUP;

        }
        OneToMany umParaMuitos = campo.getAnnotation(OneToMany.class);

        if (umParaMuitos != null) {
            tipoDoCampo = LOOKUPMULTIPLO;

        }

        sbCampo = tipoDoCampo.getRegistro();
        sbCampo.setLabel(campo.getName());

        // CONFIGURANDO OPÇÕES DE SELEÇÃO
        switch (sbCampo.getTipoCampo()) {
            case LOOKUP:
                
                if (umParaMuitos!=null){
                Class classeOpcoes = muitosParaUm.targetEntity();
                sbCampo.setListaDeOpcoes(UtilSBPersistencia.getListaTodos(classeOpcoes));
                }
                
                
                break;
            case LOOKUPMULTIPLO:
                if (umParaMuitos!=null) {
                Class classeOpcoesMultiplo = umParaMuitos.targetEntity();
                sbCampo.setListaDeOpcoes(UtilSBPersistencia.getListaTodos(classeOpcoesMultiplo));
                }
                break;
        }

        if (anotacaoInfoCampo != null) {

            sbCampo.setMascara(anotacaoInfoCampo.Mask());
            if (anotacaoInfoCampo.label().length() > 0) {
                sbCampo.setLabel(anotacaoInfoCampo.label());
            }
            sbCampo.setObrigatorio(anotacaoInfoCampo.obrigatorio());
            anotacaoInfoCampo.valoresAceitos();
        }

        Annotation[] outrasAnotacoes = campo.getAnnotations();

        NotNull nulo = campo.getAnnotation(NotNull.class);
        if (nulo != null) {
            sbCampo.setObrigatorio(true);
        }

        for (Annotation anotacao : outrasAnotacoes) {
            // Verificar outras anotacoes
        }

        return sbCampo;
    }

}
