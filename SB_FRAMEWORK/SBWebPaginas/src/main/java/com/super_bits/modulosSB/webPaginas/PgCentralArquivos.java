/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas;

import com.super_bits.modulosSB.Persistencia.util.UtilSBPersistenciaArquivosDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_PaginaConversation;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;
import javax.servlet.http.Part;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author desenvolvedor
 */
@Named
@SessionScoped
public class PgCentralArquivos implements Serializable {

    private ItfBeanSimples entidadeRelacionada;
    private Part arquivo;

    private UploadedFile uploadedFile;

    private String categoria;

    private File file;
    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        System.out.println("binding filename");
        this.filename = filename;
    }

    public String submit() {
        System.out.println("calling submit");

        if (file != null) {
            this.filename = file.getName();
        }

        System.out.println("processed");
// do what you want with the file
        return "yeah";
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    private String someText;

    public String getSomeText() {
        return someText;
    }

    public void setSomeText(String someText) {
        System.out.println("binding someText");
        this.someText = someText;
    }

    public void importa() {
        try {
            if (arquivo == null) {
                System.out.println("Arquivo é nulo");
            }
            String conteudo = new Scanner(arquivo.getInputStream())
                    .useDelimiter("\\A").next();

            System.out.println("Arquivo importando com sucesso");
        } catch (IOException e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro importando arquivo", e);
        }
    }

    public void enviarArquivoDeEntidadeSimples() {
        if (uploadedFile != null) {
            System.out.println("Arquivo Enviado=" + uploadedFile.getFileName());
        } else {
            System.out.println("Arquivo de entidade é nulo");
        }

    }

    public void enviarArquivoDeEntidade(FileUploadEvent event) {
        System.out.println("Arquivio" + event.getFile().getFileName());

        if (uploadedFile != null) {
            System.out.println("Arquivo Enviado=" + uploadedFile.getFileName());
        } else {
            System.out.println("Arquivo de entide é nulo");
        }

        System.out.println("Envio de arquivo:");
        System.out.println(entidadeRelacionada.getNome());
        System.out.println(categoria);
    }

    public void salvarImagens(FileUploadEvent event) {
        try {
            UtilSBPersistenciaArquivosDeEntidade.SalvaIMAGEM(entidadeRelacionada, event.getFile().getInputstream());
        } catch (IOException ex) {
            Logger.getLogger(PgCentralArquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ItfBeanSimples getEntidadeRelacionada() {
        return entidadeRelacionada;
    }

    public void setEntidadeRelacionada(ItfBeanSimples entidadeRelacionada) {
        this.entidadeRelacionada = entidadeRelacionada;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public Part getArquivo() {
        return arquivo;
    }

    public void setArquivo(Part arquivo) {
        this.arquivo = arquivo;
    }

}
