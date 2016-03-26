/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.validacaoRegistro.CampoInvalido;
import java.lang.reflect.Field;
import java.util.List;

/**
 *
 *
 * Um ben generico é o Object do mundo Framework Superbits.
 *
 * O bean generico sbrescreve os metodos tostring, proporcionando a utilização
 * do metodo equals de acordo com a regra de negócio dá poderes de validação e
 * informações complementares para exibição do campo, seja em um projeto Web,
 * desktop\JavaSwing, Mobile, e outros.
 *
 *
 * para as propriedades
 *
 *
 * @author sfurbino
 */
public interface ItfBeanGenerico {

    public String getNomeCampo(FabCampos pInfocampo);

    public Field getCampoReflexaoByAnotacao(FabCampos pInfoCampo);

    /**
     * Método utilizado para fazer uploads de arquivos vinculado a este registro
     *
     * @param pTipo Tipo de fonte,
     * @param pRecurso
     */
    public void uploadFoto(TipoFonteUpload pTipo, Object pRecurso);

    /**
     *
     * Cria mensagens de campos inválidos para utilização em validação
     *
     *
     * @return Uma lista de Mensagens de inconformidades com a validação
     */
    public List<CampoInvalido> getCamposInvalidos();

    /**
     *
     * Retorna os campos instanciados que foram invalidados
     *
     * @return campos instanciados que não foram validadados
     */
    public List<ItfCampoInstanciado> getCamposInstaciadosInvalidos();

    /**
     *
     * @param pNome nome do campo declarado, ou nome Da anotação do tipo
     * InfoCampo que
     * @return O Objeto campo instanciado, contendo get e set para obter os
     * valores, Label, Tipo do campo, Validação e outras informaçoes importantes
     * sobre o campo
     */
    public ItfCampoInstanciado getCampoByNomeOuAnotacao(String pNome);

    public void configIDFromNomeCurto();

    /**
     *
     * Retorna todas as entidades vinculadas ao bean para merg
     *
     * @return
     */
    public List<CaminhoCampoReflexao> getEntidadesVinculadas();

    public ItfBeanSimples getBeanSimplesPorNomeCampo(String pNomeCampo);

    public ItfBeanSimples getItemPorCaminhoCampo(CaminhoCampoReflexao pCaminho);

}
