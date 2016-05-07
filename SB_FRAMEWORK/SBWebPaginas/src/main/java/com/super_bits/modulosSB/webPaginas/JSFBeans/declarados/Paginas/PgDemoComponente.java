/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas;

import com.super_bits.modulos.SBAcessosModel.controller.FabAcaoSeguranca;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.ManipulaArquivo.UtilSBCoreArquivos;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFBeans.tipos.FabVisualizacaoCampo;
import com.super_bits.modulos.SBAcessosModel.fabricas.acoesDemonstracao.FabAcaoDemonstracaoSB;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import com.super_bits.modulos.SBAcessosModel.fabricas.acoesDemonstracao.InfoAcaoDoSistemaSB;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.vip.superkompras.webPaginas.BeanExemplo;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;

/**
 * ATENÇÃO A DOCUMENTAÇÃO DA CLASSE É OBRIGATÓRIA O JAVADOC DOS METODOS PUBLICOS
 * SÃO OBRIGATÓRIOS E QUANDO EXISTIR UMA INTERFACE DOCUMENTADA UMA REFERENCIA
 * DEVE SER CRIADA, A SINTAXE DE REFERENCIA É: @see_ NomeDAClasse#Metodo
 * DOCUMENTE DE FORMA OBJETIVA E EFICIENTE, NÃO ESQUEÇA QUE VOCÊ FAZ PARTE DE
 * UMA EQUIPE.
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 30/12/2015
 * @version 1.0
 *
 */
@SessionScoped
@Named
@InfoPagina(nomeCurto = "DC", tags = {"Demo componentes"})
@InfoAcaoDoSistemaSB(acao = FabAcaoDemonstracaoSB.DEMONSTRACAO_MB_COMPONENTE)
public class PgDemoComponente extends MB_PaginaConversation {

    private final BeanExemplo beanExemplo;
    private final AcaoDoSistema acaoSalvar = FabAcaoSeguranca.GRUPO__CTR_SALVAR_MERGE.getAcaoDoSistema();
    private final AcaoDoSistema acaoVoltar = FabAcaoSeguranca.GRUPO_FRM_LISTAR.getAcaoDoSistema();
    private final AcaoDoSistema acaoExcluir = FabAcaoSeguranca.GRUPO_CTR_ALTERAR_STATUS.getAcaoDoSistema();
    private List<AcaoDoSistema> listaDeAcaoes2;
    private List<AcaoDoSistema> listaDeAcaoes3;
    private List<AcaoDoSistema> listaDeAcaoes4;
    private List<AcaoDoSistema> listaDeAcaoes5;
    private List<AcaoDoSistema> listaDeAcaoes6;
    private AcaoDoSistema acaoSelecionada;
    private List<String> listaFormularios;
    private String formulario;
    private int diferencialIDDinamico;

    private ItfCampoInstanciado campoSelecionado;

    private List<String> listatiposVisualizacao;
    private String tipoVisualizacao;

    public PgDemoComponente() {
        super();
        beanExemplo = new BeanExemplo();
    }

    public void validacao() {
        System.out.println("Validou");
        diferencialIDDinamico++;
    }

    @PostConstruct
    public void inicio() {
        listaDeAcaoes2.add(acaoSalvar);
        listaDeAcaoes2.add(acaoExcluir);
        listaDeAcaoes3.add(acaoSalvar);
        listaDeAcaoes3.add(acaoExcluir);
        listaDeAcaoes3.add(acaoVoltar);
        listaDeAcaoes4.add(acaoSalvar);
        listaDeAcaoes4.add(acaoExcluir);
        listaDeAcaoes4.add(acaoVoltar);
        listaDeAcaoes4.add(acaoSalvar);
        listaDeAcaoes6.add(acaoSalvar);
        listaDeAcaoes6.add(acaoExcluir);
        listaDeAcaoes6.add(acaoVoltar);
        listaDeAcaoes6.add(acaoSalvar);
        listaDeAcaoes6.add(acaoVoltar);
        listaDeAcaoes6.add(acaoSalvar);
        listatiposVisualizacao = new ArrayList<>();
        String caminhoRelativoExemplos = "/resources/exemplosAdamantium/";
        String caminhoExemplos = UtilSBWPServletTools.getCaminhoLocalServletsResource() + "/exemplosAdamantium/";
        List<String> arquivos = UtilSBCoreArquivos.getArquivosDoDiretorio(caminhoExemplos);

        listaFormularios = new ArrayList<>();
        for (String arq : arquivos) {
            listaFormularios.add(caminhoRelativoExemplos + arq);
        }

        formulario = caminhoRelativoExemplos + "formularios.xhtml";

        // configurando tipos de visualização de campos input
        for (FabVisualizacaoCampo tipo : FabVisualizacaoCampo.class.getEnumConstants()) {
            listatiposVisualizacao.add(tipo.toString());
        }
        tipoVisualizacao = listatiposVisualizacao.get(0);
    }

    @Override
    protected String defineTitulo() {
        return "Demonstração de Layouts";
    }

    @Override
    protected String defineNomeLink() {
        return "DemoComponents";
    }

    @Override
    protected String defineDescricao() {
        return "Pagina de demonstração de componentes";
    }

    @Override
    public int getId() {
        return -10;
    }

    public BeanExemplo getBeanExemplo() {
        return beanExemplo;
    }

    public AcaoDoSistema getAcaoSalvar() {
        return acaoSalvar;
    }

    public AcaoDoSistema getAcaoVoltar() {
        return acaoVoltar;
    }

    public AcaoDoSistema getAcaoExcluir() {
        return acaoExcluir;
    }

    public List<AcaoDoSistema> getListaDeAcaoes2() {
        return listaDeAcaoes2;
    }

    public void setListaDeAcaoes2(List<AcaoDoSistema> listaDeAcaoes2) {
        this.listaDeAcaoes2 = listaDeAcaoes2;
    }

    public List<AcaoDoSistema> getListaDeAcaoes3() {
        return listaDeAcaoes3;
    }

    public void setListaDeAcaoes3(List<AcaoDoSistema> listaDeAcaoes3) {
        this.listaDeAcaoes3 = listaDeAcaoes3;
    }

    public List<AcaoDoSistema> getListaDeAcaoes4() {
        return listaDeAcaoes4;
    }

    public void setListaDeAcaoes4(List<AcaoDoSistema> listaDeAcaoes4) {
        this.listaDeAcaoes4 = listaDeAcaoes4;
    }

    public List<AcaoDoSistema> getListaDeAcaoes5() {
        return listaDeAcaoes5;
    }

    public void setListaDeAcaoes5(List<AcaoDoSistema> listaDeAcaoes5) {
        this.listaDeAcaoes5 = listaDeAcaoes5;
    }

    public List<AcaoDoSistema> getListaDeAcaoes6() {
        return listaDeAcaoes6;
    }

    public void setListaDeAcaoes6(List<AcaoDoSistema> listaDeAcaoes6) {
        this.listaDeAcaoes6 = listaDeAcaoes6;
    }

    public AcaoDoSistema getAcaoSelecionada() {
        return acaoSelecionada;
    }

    public void setAcaoSelecionada(AcaoDoSistema acaoSelecionada) {
        this.acaoSelecionada = acaoSelecionada;
    }

    public List<String> getListatiposVisualizacao() {
        return listatiposVisualizacao;
    }

    public String getTipoVisualizacao() {
        return tipoVisualizacao;
    }

    public void setTipoVisualizacao(String tipoVisualizacao) {
        this.tipoVisualizacao = tipoVisualizacao;
    }

    public List<String> getListaFormularios() {
        return listaFormularios;
    }

    public String getFormulario() {
        return formulario;
    }

    public void setFormulario(String formulario) {
        this.formulario = formulario;
    }

    public ItfCampoInstanciado getCampoSelecionado() {
        return campoSelecionado;
    }

    public void setCampoSelecionado(ItfCampoInstanciado campoSelecionado) {
        this.campoSelecionado = campoSelecionado;
    }

    public int getDiferencialIDDinamico() {
        return diferencialIDDinamico;
    }

}
