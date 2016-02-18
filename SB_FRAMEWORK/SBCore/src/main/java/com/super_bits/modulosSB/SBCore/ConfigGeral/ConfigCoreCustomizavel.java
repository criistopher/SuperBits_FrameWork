/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.Controller.ConfigPermissaoAbstratoSBCore;
import com.super_bits.Controller.Interfaces.ItfCfgPermissoes;
import com.super_bits.modulosSB.SBCore.Mensagens.ItfCentralMensagens;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.InfoErroSB;
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
    private Class<? extends InfoErroSB> classeErro;
    private Class<? extends ItfControleDeSessao> controleDeSessao;
    private Class<? extends ItfCentralEventos> centralDeEventos;
    private Class<? extends ConfigPermissaoAbstratoSBCore> classeConfigPermissao;
    private String nomeProjeto;
    private SBCore.ESTADO_APP estadoAPP;
    private String cliente;
    private String grupoProjeto;
    private String diretorioBase = "/";

    @Override
    public Class<? extends ItfCentralMensagens> getCentralDeMensagens() {

        return centralMEnsagens;
    }

    @Override
    public Class<? extends InfoErroSB> getClasseErro() {
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

    public void setClasseErro(Class<? extends InfoErroSB> classeErro) {
        this.classeErro = classeErro;
    }

    public void setControleDeSessao(Class<? extends ItfControleDeSessao> controleDeSessao) {
        this.controleDeSessao = controleDeSessao;
    }

    public void setCentralDeEventos(Class<? extends ItfCentralEventos> centralDeEventos) {
        this.centralDeEventos = centralDeEventos;
    }

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

}
