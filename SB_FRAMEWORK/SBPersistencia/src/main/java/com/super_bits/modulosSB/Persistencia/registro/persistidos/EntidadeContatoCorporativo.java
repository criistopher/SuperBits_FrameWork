package com.super_bits.modulosSB.Persistencia.registro.persistidos;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanContatoCorporativo;
import javax.persistence.EntityManager;

public abstract class EntidadeContatoCorporativo extends EntidadeEndereco implements
        ItfBeanContatoCorporativo {

    /**
     *
     * Carrega a empresa localizando-a através das informações de contato, que
     * podem ser: NOME, TELEFONE, SITE
     *
     *
     * @param pTelefon_nome_cnpj Parametro
     * @param pEm Entity Manager
     */
    public boolean loadEmpresabyContato(String pTelefon_nome_cnpj, EntityManager pEm) {
        Object registroEncontrado = UtilSBPersistencia.getEmpresa(this.getClass(), pTelefon_nome_cnpj, pEm);
        if (registroEncontrado == null) {
            return false;
        }
        copiaDados(registroEncontrado);
        return true;
    }

    public EntidadeContatoCorporativo(Class<?> pClasseModelo) {
        super(pClasseModelo);
        // TODO implementar EntidadeContatoCorporativo
    }

    @Override
    public String getSite() {

        return (String) getValorByTipoCampoEsperado(FabCampos.SITE);
    }

    @Override
    public String telefone() {
        return (String) getValorByTipoCampoEsperado(FabCampos.TELEFONE_FIXO_NACIONAL);
    }

    @Override
    public String responsavel() {
        return (String) getValorByTipoCampoEsperado(FabCampos.RESPONSAVEL);
    }

}
