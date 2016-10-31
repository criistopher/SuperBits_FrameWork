/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.arquivosDoProjeto;

import com.super_bits.modulosSB.Persistencia.util.UtilSBPersistenciaArquivosDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.FabTipoEmpacotamento;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.CentralDeArquivosAbstrata;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salvioF
 */
public class CentralDeArquivosWebApp extends CentralDeArquivosAbstrata {

    private final String PASTA_IMAGEM = "img";
    private final String PASTA_ARQUIVOS_ENTIDADE = "arquivos";

    private String endrLocaResource;

    private String endrRemotoResource;

    public CentralDeArquivosWebApp() {
        super(FabTipoEmpacotamento.SITE_WAR);
        //UtilSBPersistenciaArquivosDeEntidade.getEndrRemotoImagem(item, FabCampos.ID)
    }

    @Override
    public String getEndrLocalResources() {
        if (endrLocaResource == null) {
            endrLocaResource = UtilSBWPServletTools.getCaminhoLocalServletsResource();
        }
        return endrLocaResource;

    }

    @Override
    public String getEndrRemotoResources() {

        try {
            if (endrRemotoResource == null) {
                if (SBCore.isEmModoDesenvolvimento()) {
                    endrRemotoResource = SBWebPaginas.getCaminhoWebAppDeveloper();
                } else {
                    endrRemotoResource = UtilSBWPServletTools.getCaminhoLocalServlet();
                }
            }
            return endrRemotoResource;

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro definindo endereço remoto para REsource", t);
            return null;

        }
    }

    @Override
    public String getEndrRemotoResourcesObjeto() {
        return getEndrLocalResources() + "/" + PASTA_ARQUIVOS_ENTIDADE;
    }

    @Override
    public String getEndrLocalResourcesObjeto() {
        return getEndrLocalResources() + "/" + PASTA_ARQUIVOS_ENTIDADE;
    }

    @Override
    public String getEndrRemotoRecursosDoObjeto(Class entidade) {
        return getEndrRemotoResourcesObjeto() + "/" + entidade.getSimpleName() + "/";
    }

    @Override
    public List<String> getEndrsLocaisDeCategoriasItem(ItfBeanSimples item) {
        List<String> pastasCategoria = new ArrayList<>();
        File pastaItem = new File(getEndrLocalRecursosDoObjeto(item.getClass()));

        for (File arquivoDaPasta : pastaItem.listFiles()) {
            if (arquivoDaPasta.isDirectory()) {
                pastasCategoria.add(arquivoDaPasta.getAbsolutePath());
            }
        }

        return pastasCategoria;

    }

    @Override
    public List<String> getNomesPastasCategoriasItem(ItfBeanSimples item) {
        List<String> pastasCategoria = new ArrayList<>();
        try {

            File pastaItem = new File(getEndrLocalRecursosDoObjeto(item.getClass()));
            if (pastaItem.exists()) {
                for (File arquivoDaPasta : pastaItem.listFiles()) {
                    if (arquivoDaPasta.isDirectory()) {
                        pastasCategoria.add(arquivoDaPasta.getName());
                    }
                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo nomes de categorias do item" + item, t);
        }

        return pastasCategoria;
    }

    @Override
    public String getEndrLocalImagens() {
        return getEndrLocalResources() + "/" + getNomeLocalPastaImagem();
    }

    @Override
    public String getEndrRemotoImagens() {

        return getEndrRemotoResources() + "/" + getNomeRemotoPastaImagem();

    }

    @Override
    public String getNomeRemotoPastaImagem() {
        return PASTA_IMAGEM;
    }

    @Override
    public String getNomeLocalPastaImagem() {
        return PASTA_IMAGEM;
    }

    @Override
    public String getEndrLocalRecursosDoObjeto(Class entidade) {
        return getEndrLocalResourcesObjeto() + "/" + entidade.getSimpleName() + "/";
    }

    @Override
    public String getEndrLocalRecursosDoObjeto(Class entidade, String pGaleria) {

        if (pGaleria == null) {
            return getEndrLocalRecursosDoObjeto(entidade);
        } else {
            return getEndrLocalRecursosDoObjeto(entidade) + "/" + pGaleria;
        }

    }

    @Override
    public String getEndrRemotoIMGPadraoPorTipoCampo(FabCampos tipo) {
        return getEndrRemotoResourcesObjeto() + "/" + tipo.toString() + ".jpg";
    }

    @Override
    public String getEntdrRemotoIMGPadraoPorTipoClasse(Class entidade) {
        return getEndrRemotoRecursosDoObjeto(entidade) + "/";
    }

    @Override
    public String getEndrRemotoImagem(ItfBeanSimples item, FabCampos tipo) {
        switch (tipo) {

            case IMG_PEQUENA:
                break;
            case IMG_MEDIA:
                break;
            case IMG_GRANDE:
                return UtilSBPersistenciaArquivosDeEntidade.getURLImagem(item, tipo);
            default:
                return getEndrRemotoRecursosDoObjeto(item.getClass()) + "/" + tipo.toString() + ".jpg";

        }
        return getEndrRemotoRecursosDoObjeto(item.getClass()) + "/" + tipo.toString() + ".jpg";

    }

    @Override
    public String getEndrRemotoRecursosItem(ItfBeanSimples item, String galeria) {
        if (galeria == null) {
            return getEndrRemotoRecursosDoObjeto(item.getClass());
        } else {
            return getEndrRemotoRecursosDoObjeto(item.getClass()) + "/" + galeria;
        }
    }

    @Override
    public String getEndrRemotoRecursosItem(ItfBeanSimples item) {

        return getEndrRemotoRecursosDoObjeto(item.getClass());

    }

    @Override
    public void salvarImagem(ItfBeanSimples entidade, InputStream foto, String categoria) {
        UtilSBPersistenciaArquivosDeEntidade.SalvaIMAGEM(entidade, foto, categoria);
    }

    @Override
    public List<String> getEnterecosLocaisRecursosItem(ItfBeanSimples item, String galeria) {
        List<String> recursosDoItem = new ArrayList<>();
        getEndrLocalRecursosDoObjeto(item.getClass(), galeria);

        return recursosDoItem;
    }

    @Override
    public List<String> getEnterecosRemotosRecursosItem(ItfBeanSimples item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndrLocalArquivoItem(ItemSimples pItem, String nomeArquivo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndrLocalArquivoItem(ItemSimples pItem, String nomeArquivo, String categoria) {
        if (categoria == null) {
            return getEndrLocalRecursosDoObjeto(pItem.getClass()) + "/" + nomeArquivo;
        } else {
            return getEndrLocalRecursosDoObjeto(pItem.getClass()) + "/" + categoria + "/" + nomeArquivo;
        }

    }

    @Override
    public String getEndrRemotoArquivoItem(ItemSimples pItem, String nomeArquivo) {

        return getEndrRemotoRecursosDoObjeto(pItem.getClass()) + "/" + nomeArquivo;

    }

    @Override
    public String getEndrRemotoArquivoItem(ItemSimples pItem, String nomeArquivo, String categoria) {
        return getEndrRemotoRecursosDoObjeto(pItem.getClass()) + "/ " + categoria + "/" + nomeArquivo;
    }

}
