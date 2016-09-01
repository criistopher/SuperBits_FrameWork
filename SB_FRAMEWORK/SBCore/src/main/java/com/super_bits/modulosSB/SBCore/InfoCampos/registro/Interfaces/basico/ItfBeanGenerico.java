/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico;

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

    /**
     * Método utilizado para fazer uploads de arquivos vinculado a este registro
     *
     * @param pTipo Tipo de fonte,
     * @param pRecurso
     */
    public void uploadFoto(TipoFonteUpload pTipo, Object pRecurso);

    public String getImgPequena();

    /**
     *
     * Configura o Id da entidade apartir do nome.
     *
     * Utilizado para integrar registros de APIS diferentes, como Apis de CEPS
     *
     * O método retira os espaços e caracteres especiais, coloca tudo minusculo,
     * e cria um hash correspontende ao id
     *
     */
    public void configIDPeloNome();

    public String getNomeDoObjeto();

    /**
     *
     * @param nomeDaLista
     */
    public void adicionarItemNaLista(String nomeDaLista);
}
