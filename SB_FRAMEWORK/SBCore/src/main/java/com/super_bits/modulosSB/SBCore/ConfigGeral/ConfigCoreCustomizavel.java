/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.Controller.ConfigPermissaoAbstratoSBCore;
import com.super_bits.modulosSB.SBCore.Mensagens.ItfCentralMensagens;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.InfoErroSBComAcoes;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.logeventos.ItfCentralEventos;
import com.super_bits.modulosSB.SBCore.sessao.Interfaces.ItfControleDeSessao;

/**
 *
 * Classe para criação dinimica de configuradores
 *
 *
 * @author cristopher
 */
public class ConfigCoreCustomizavel implements ItfConfiguradorCore {

    private Class<? extends ItfCentralMensagens> centralMEnsagens;
    private Class<? extends InfoErroSBComAcoes> classeErro;
    private Class<? extends ItfControleDeSessao> controleDeSessao;
    private Class<? extends ItfCentralEventos> centralDeEventos;
    private Class<? extends ConfigPermissaoAbstratoSBCore> classeConfigPermissao;
    private String nomeProjeto;
    private SBCore.ESTADO_APP estadoAPP;
    private String cliente;
    private String grupoProjeto;
    private String diretorioBase = "";
    private Class<? extends ItfFabricaAcoes>[] acoesDoSistema;
    private String urlJira;

    @Override
    public Class<? extends ItfCentralMensagens> getCentralDeMensagens() {

        return centralMEnsagens;
    }

    @Override
    public Class<? extends InfoErroSBComAcoes> getClasseErro() {
        return classeErro;
    }

    @Override
    public Class<? extends ItfControleDeSessao> getControleDeSessao() {
        return controleDeSessao;
    }

    @Override
    public Class<? extends ItfCentralEventos> getCentralDeEventos() {
        return centralDeEventos;
    }

    @Override
    public String getNomeProjeto() {
        return nomeProjeto;

    }

    @Override
    public SBCore.ESTADO_APP getEstadoApp() {
        return estadoAPP;
    }

    @Override
    public String getDiretorioBase() {
        return diretorioBase;

    }

    @Override
    public String getCliente() {
        return cliente;
    }

    @Override
    public String getGrupoProjeto() {
        return grupoProjeto;
    }

    public void setCentralMEnsagens(Class<? extends ItfCentralMensagens> centralMEnsagens) {
        this.centralMEnsagens = centralMEnsagens;
    }

    public void setClasseErro(Class<? extends InfoErroSBComAcoes> classeErro) {
        this.classeErro = classeErro;
    }

    public void setControleDeSessao(Class<? extends ItfControleDeSessao> controleDeSessao) {
        this.controleDeSessao = controleDeSessao;
    }

    public void setCentralDeEventos(Class<? extends ItfCentralEventos> centralDeEventos) {
        this.centralDeEventos = centralDeEventos;
    }

    /**
     *
     * O nome do projeto identifica a pasta onde o projeto se encontra
     *
     * @param nomeProjeto
     */
    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public void setEstadoAPP(SBCore.ESTADO_APP estadoAPP) {
        this.estadoAPP = estadoAPP;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setGrupoProjeto(String grupoProjeto) {
        this.grupoProjeto = grupoProjeto;
    }

    /**
     *
     * O diretorio base é o diretorio que pode existir logo depois da pasta
     * source, agrupando diversos projetos
     *
     * @param diretorioBase
     */
    public void setDiretorioBase(String diretorioBase) {
        this.diretorioBase = diretorioBase;
    }

    public void setClasseConfigPermissao(Class<? extends ConfigPermissaoAbstratoSBCore> pConfigPermissao) {
        classeConfigPermissao = pConfigPermissao;
    }

    @Override
    public Class<? extends ConfigPermissaoAbstratoSBCore> getConfigPermissoes() {
        return classeConfigPermissao;
    }

    @Override
    public Class<? extends ItfFabricaAcoes>[] getFabricaDeAcoes() {
        return acoesDoSistema;
    }

    public void setFabricaDeAcoes(Class<? extends ItfFabricaAcoes>[] pAcoes) {
        acoesDoSistema = pAcoes;
    }

    @Override
    public String getUrlJira() {
        return urlJira;
    }

    public void setUrlJira(String urlJira) {
        this.urlJira = urlJira;
    }

}
