/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.centralDeArquivos;

import com.super_bits.modulosSB.Persistencia.util.UtilSBPersistenciaArquivosDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringNomeArquivosEDiretorios;
import com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao.MapaSubstituicaoArquivo;

import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAnonimo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author desenvolvedor
 */
@Named
@ViewScoped
public class PgCentralArquivos implements Serializable {

    private ItfBeanSimples entidadeRelacionada;
    private String categoria;
    private String nomeNovaEntidade;
    private String nomeArquivo = "[nomeArquivo]";
    private FabTipoEnvioArquivoEntidade tipoEnvio = FabTipoEnvioArquivoEntidade.ARQUIVO_DA_ENTIDADE;
    private MapaSubstituicaoArquivo substituicaoEmArquivoExemplo;

    public void enviarArquivoCriandoEntidadeSelecionada(FileUploadEvent event) {

    }

    @PostConstruct
    public void init() {
        substituicaoEmArquivoExemplo = new MapaSubstituicaoArquivo(new File("/home/teste.txt"));
    }

    public List<String> getPalavrasChaveSubstituicaoArquivo() {
        return substituicaoEmArquivoExemplo.getpalavrasChave();
    }

    public void alterarTipoEnvio(FabTipoEnvioArquivoEntidade pTipoEnvioArquivoEntidade) {
        tipoEnvio = pTipoEnvioArquivoEntidade;
    }

    public void enviarArquivoDeEntidadeSelecionada(FileUploadEvent event) {
        if (SBCore.getControleDeSessao().getSessaoAtual().isIdentificado()) {
            throw new UnsupportedOperationException("Ouve uma tentativa não autorizada de enviar arquivos de entidade");
        }
        try {
            if (tipoEnvio == null) {
                throw new UnsupportedOperationException("O tipo de envio não foi selecionado, defina o tipo de envio para proceguir");
            }

            switch (tipoEnvio) {
                case ARQUIVO_DA_ENTIDADE:

                    break;
                case ARQUIVO_CRIANDO_NOVA_ENTIDADE:

                    if (nomeNovaEntidade == null) {
                        throw new UnsupportedOperationException("O nome da classe de entidade que deve ser criada não foi configurado");
                    }

                    Class classeRelacionada = MapaObjetosProjetoAtual.getClasseDoObjetoByNome(nomeNovaEntidade);
                    if (classeRelacionada == null) {
                        throw new UnsupportedOperationException("a entidade não foi encontrada no projeto: " + nomeNovaEntidade);
                    }

                    ItfBeanSimples teste = (ItfBeanSimples) classeRelacionada.newInstance();
                    event.getFile();
                    String nomeDoArquivo = UtilSBCoreStringNomeArquivosEDiretorios.getNomeDoArquivoSemExtencao(event.getFile().getFileName());
                    String nomeNovoArquivo = nomeDoArquivo;
                    MapaSubstituicaoArquivo substituicoesEmArquivo = new MapaSubstituicaoArquivo(new File(event.getFile().getFileName()));
                    nomeNovoArquivo = substituicoesEmArquivo.substituirEmString(categoria);
                    if (nomeArquivo == null) {
                        nomeArquivo = nomeDoArquivo;
                    }
                    teste.setNome(nomeNovoArquivo);

                    break;
                case IMAGEM_REPRESENTANTE_ENTIDADE:

                    break;
                default:
                    throw new AssertionError(tipoEnvio.name());

            }

            System.out.println("Enviando para " + entidadeRelacionada);
            System.out.println("Nome Do arquivo" + nomeArquivo);
            System.out.println("nome Entidade" + nomeNovaEntidade);
            System.out.println("Envio de arquivo:");
            System.out.println(entidadeRelacionada);
            System.out.println(categoria);
        } catch (Throwable t) {
            SBCore.enviarMensagemUsuario("Ouve um erro inesperado ao enviar o arquivo para o servidor", FabMensagens.ERRO);
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro inesperado tentando enviar arquivo", t);
        }
    }

    public void salvarImagens(FileUploadEvent event) {
        try {
            UtilSBPersistenciaArquivosDeEntidade.SalvaIMAGEM(entidadeRelacionada, event.getFile().getInputstream());
        } catch (IOException ex) {
            Logger.getLogger(PgCentralArquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNomeNovaEntidade() {
        return nomeNovaEntidade;
    }

    public void setNomeNovaEntidade(String nomeNovaEntidade) {
        this.nomeNovaEntidade = nomeNovaEntidade;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
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

}
