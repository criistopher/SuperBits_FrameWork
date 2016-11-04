/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.centralDeArquivos;

import com.super_bits.modulosSB.Persistencia.util.UtilSBPersistenciaArquivosDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringNomeArquivosEDiretorios;
import com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao.MapaSubstituicaoArquivo;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivos;

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
        if (!SBCore.getControleDeSessao().getSessaoAtual().isIdentificado()) {
            throw new UnsupportedOperationException("Ouve uma tentativa não autorizada de enviar arquivos de entidade");
        }
        String caminhoSalvarArquivo = "caminho nao definido";
        try {
            if (tipoEnvio == null) {
                throw new UnsupportedOperationException("O tipo de envio não foi selecionado, defina o tipo de envio para proceguir");
            }

            if (categoria == null) {
                throw new UnsupportedOperationException("o nome da categoria precisa ser configurada");
            }
            switch (tipoEnvio) {
                case ARQUIVO_DA_ENTIDADE:

                    break;
                case IMAGEM_REPRESENTANTE_ENTIDADE:
                    break;
                default:
                    throw new AssertionError(tipoEnvio.name());
            }
            if (UtilSBCoreArquivos.isArquivoExiste(caminhoSalvarArquivo)) {
                SBCore.enviarMensagemUsuario("O arquivo já foi enviado para o sistema", FabMensagens.AVISO);
                throw new UnsupportedOperationException("Este arquivo já existe");
            }
            SBCore.getCentralDeArquivos().salvarArquivo(entidadeRelacionada, event.getFile().getInputstream(), event.getFile().getFileName(), categoria);
            System.out.println("Salvando arquivo EM " + caminhoSalvarArquivo);

        } catch (Throwable t) {
            SBCore.enviarMensagemUsuario("Ouve um erro inesperado ao enviar o arquivo para o servidor", FabMensagens.ERRO);
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro inesperado tentando enviar arquivo", t);
            throw new UnsupportedOperationException("Ocorreu um erro tentando salvar o arquivo");
        }
    }

    public void salvarImagens(FileUploadEvent event) {
        try {
            UtilSBPersistenciaArquivosDeEntidade.SalvaIMAGEM(entidadeRelacionada, event.getFile().getInputstream());
        } catch (IOException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro salvando imagem para" + entidadeRelacionada, ex);
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

}
