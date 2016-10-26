/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo;

import com.super_bits.modulosSB.SBCore.ConfigGeral.FabTipoEmpacotamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salvioF
 */
public class CentralDeArquivosJar extends CentralDeArquivosAbstrata {

    private static final String textoPadrao = "Central de arquivos em Jars ainda não foi implementado";

    public CentralDeArquivosJar() {
        super(FabTipoEmpacotamento.BIBLIOTECA_JAR);
        System.out.println(textoPadrao);
    }

    @Override
    public String getEndrLocalResources() {
        return textoPadrao;
    }

    @Override
    public String getEndrLocalResourcesObjeto() {
        return textoPadrao;
    }

    @Override
    public String getEndrLocalImagens() {
        return textoPadrao;
    }

    @Override
    public String getEndrRemotoImagens() {
        return textoPadrao;
    }

    @Override
    public String getNomeRemotoPastaImagem() {
        return textoPadrao;
    }

    @Override
    public String getNomeLocalPastaImagem() {
        return textoPadrao;
    }

    @Override
    public String getEndrLocalRecursosDoObjeto(Class entidade) {
        return textoPadrao;
    }

    @Override
    public String getEndrRemotoIMGPadraoPorTipoCampo(FabCampos tipo) {
        return textoPadrao;
    }

    @Override
    public String getEntdrRemotoIMGPadraoPorTipoClasse(Class entidade) {
        return textoPadrao;
    }

    @Override
    public void salvarImagem(ItfBeanSimples entidade, InputStream foto, String categoria) {
        System.out.println(textoPadrao);

    }

    @Override
    public String getEndrRemotoResources() {
        return textoPadrao;
    }

    @Override
    public String getEndrRemotoResourcesObjeto() {
        return textoPadrao;
    }

    @Override
    public String getEndrLocalRecursosDoObjeto(Class entidade, String pGaleria) {
        return textoPadrao;
    }

    @Override
    public String getEndrRemotoRecursosDoObjeto(Class entidade) {
        return textoPadrao;
    }

    @Override
    public String getEndrRemotoImagem(ItfBeanSimples item, FabCampos tipo) {
        return textoPadrao;
    }

    @Override
    public List<String> getEnterecosLocaisRecursosItem(ItfBeanSimples item, String galeria) {
        return new ArrayList<>();
    }

    @Override
    public List<String> getEnterecosRemotosRecursosItem(ItfBeanSimples item) {
        return new ArrayList<>();
    }

    @Override
    public List<String> getEndrsLocaisDeCategoriasItem(ItfBeanSimples item) {
        return new ArrayList<>();
    }

    @Override
    public List<String> getNomesPastasCategoriasItem(ItfBeanSimples item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndrLocalArquivoItem(ItemSimples pItem, String nomeArquivo) {
        return textoPadrao;
    }

    @Override
    public String getEndrLocalArquivoItem(ItemSimples pItem, String nomeArquivo, String Categoria) {
        return textoPadrao;
    }

    @Override
    public String getEndrRemotoArquivoItem(ItemSimples pItem, String nomeArquivo) {
        return textoPadrao;
    }

    @Override
    public String getEndrRemotoArquivoItem(ItemSimples pItem, String nomeArquivo, String Categoria) {
        return textoPadrao;
    }

    @Override
    public String getEndrRemotoRecursosItem(ItfBeanSimples item, String galeria) {
        return textoPadrao;
    }

    @Override
    public String getEndrRemotoRecursosItem(ItfBeanSimples item) {
        return textoPadrao;
    }

}
