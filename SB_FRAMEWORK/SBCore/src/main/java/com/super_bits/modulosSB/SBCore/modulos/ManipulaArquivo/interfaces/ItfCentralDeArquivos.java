/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.interfaces;

import com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao.MapaSubstituicao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.io.InputStream;
import java.util.List;

/**
 *
 *
 *
 *
 *
 *
 *
 * @author salvioF
 */
public interface ItfCentralDeArquivos {

    public final static String PALAVRA_CHAVE_NOME_ARQUIVO = "[nomeArquivo]";
    public final static String PALAVRA_CHAVE_TRECHO_CONTEUDO = "[trechoConteudo]";
    public final static String CATEGORIA_PADRAO_ARQUIVO_DE_REGISTRO = "arquivoDeRegistro";

    /**
     * Retorna o caminho completo onde todos os arquivos do projeto ficarão
     * armazenados
     *
     * @return caminho onde todos os arquivos do projeto ficarão armazenados
     */
    public String getEndrLocalResources();

    public String getEndrRemotoResources();

    /**
     *
     * Retorna o caminho completo onde ficarão os arquivos referentes a
     * entidades ex de arquivos de entidade
     * diretorioAplicacao/pastaLocalResources/pastaLocalResourceObjeto/Cliente/1/fotoDocliente.jpg
     *
     * @return caminho local completo dos arquivos de objeto
     */
    public String getEndrLocalResourcesObjeto();

    public String getEndrRemotoResourcesObjeto();

    /**
     *
     * Retorna a pasta Onde as imagens do sistema são armazenadas
     *
     *
     * @return
     */
    public String getEndrLocalImagens();

    /**
     * Retorna o caminho remoto para pasta imagens: ex:
     * http://sitedoprojeto.com.br/resources/img
     *
     *
     * @return Caminho http completo para pasta de imagens
     */
    public String getEndrRemotoImagens();

    /**
     *
     * ex: /resources/img
     *
     * @return Caminho relativo ao dominio para pasta imagens
     */
    public String getNomeRemotoPastaImagem();

    /**
     *
     * Nome da pasta de imagens, ex:
     *
     * @return
     */
    public String getNomeLocalPastaImagem();

    /**
     *
     * Eto
     *
     * ex: diretorioAplicacao/pastaLocalResources/objetos/Cliente
     *
     *
     * @param entidade
     * @return
     */
    public String getEndrLocalRecursosDoObjeto(Class entidade);

    public String getEndrLocalRecursosDoObjeto(Class entidade, String pGaleria);

    /**
     *
     * ex: http://www.meuprojeto.com.br/resoureces/objetos/Cliente
     *
     * @param entidade
     * @return
     */
    public String getEndrRemotoRecursosDoObjeto(Class entidade);

    /**
     *
     * O endereço remoto que representa determinado campo padrão:
     *
     * ex: http://meuprojeto.com.br/resources/img/imagemNaoDisponivel.jpg
     *
     *
     * @param tipo
     * @return
     */
    public String getEndrRemotoIMGPadraoPorTipoCampo(FabCampos tipo);

    /**
     * O endereço remoto que representa determinada Entidade, ex:
     *
     * ex: http://meuprojeto.com.br/resources/objeto/Cliente/clienteSemFoto.jpg
     *
     * @param entidade
     * @return
     */
    public String getEntdrRemotoIMGPadraoPorTipoClasse(Class entidade);

    /**
     *
     * REtorna a URL da imagem de acordo com o tipo de campo e entidade
     *
     * * ex:
     * http://meuprojeto.com.br/resources/objeto/Cliente/campoExcentrico/imagemCampoExcentrico.jpg
     *
     * @param item
     * @param tipo
     * @return
     */
    public String getEndrRemotoImagem(ItfBeanSimples item, FabCampos tipo);

    /**
     *
     *
     *
     * @param item
     * @param galeria
     * @return
     */
    public String getEndrRemotoRecursosItem(ItfBeanSimples item, String galeria);

    /**
     *
     * @param item
     * @return
     */
    public String getEndrRemotoRecursosItem(ItfBeanSimples item);

    /**
     *
     *
     *
     * @param item
     * @param galeria
     * @return
     */
    public List<String> getEnterecosLocaisRecursosItem(ItfBeanSimples item, String galeria);

    /**
     *
     * @param item
     * @return
     */
    public List<String> getEnterecosRemotosRecursosItem(ItfBeanSimples item);

    /**
     *
     * Retorna os caminhos para pastas de categoria do objeto
     *
     * As subpastas no diretorio da classe são as categorias ex:
     *
     * diretorioAplicacao/pastaLocalResources/pastaLocalResourceObjeto/Cliente/1/categoria1/
     *
     *
     * @param item
     * @return
     */
    public List<String> getEndrsLocaisDeCategoriasItem(ItfBeanSimples item);

    /**
     * Retorna o nome das pastas de categoria do objeto
     *
     * As subpastas no diretorio da classe são as categorias ex:
     *
     * diretorioAplicacao/pastaLocalResources/pastaLocalResourceObjeto/Cliente/1/categoria1/
     *
     * @param item
     * @return
     */
    public List<String> getNomesPastasCategoriasItem(ItfBeanSimples item);

    public String getEndrLocalArquivoItem(ItfBeanSimples pItem, String nomeArquivo);

    public String getEndrLocalArquivoItem(ItfBeanSimples pItem, String nomeArquivo, String Categoria);

    public String getEndrRemotoArquivoItem(ItfBeanSimples pItem, String nomeArquivo);

    public String getEndrRemotoArquivoItem(ItfBeanSimples pItem, String nomeArquivo, String Categoria);

    /**
     *
     * @param entidade
     * @param foto
     * @param categoria
     */
    public void salvarImagem(ItfBeanSimples entidade, InputStream foto, String categoria);

    public boolean salvarArquivo(ItfBeanSimples entidade, InputStream arqivo, String nome, String categoria);

    public boolean baixarArquivo(ItfBeanSimples entidade, InputStream arqivo, String categoria, MapaSubstituicao mapaSubistituicao);

}
