/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.Controller.ConfigPermissaoAbstratoSBCore;
import com.super_bits.modulosSB.SBCore.Mensagens.CentramMensagemProgramadorMsgStop;
import com.super_bits.modulosSB.SBCore.Mensagens.ItfCentralMensagens;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ErroSBCoreDeveloperSopMessagem;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ErroSBCoreFW;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.InfoErroSBComAcoes;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.logeventos.CentralLogEventosArqTextoGenerica;
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

    @Override
    public Class<? extends ConfigPermissaoAbstratoSBCore> getConfigPermissoes() {
        return classeConfigPermissao;
    }

    @Override
    public Class<? extends ItfFabricaAcoes>[] getFabricaDeAcoes() {
        return acoesDoSistema;
    }

    @Override
    public String getUrlJira() {
        return urlJira;
    }

    public static enum TIPOS_CONFIG {
        CORE_DEVELOPER_PADRAO, WEBPAGINAS_DEVELOPER_PADRAO
    };
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

    public ConfigCoreCustomizavel() {
    }

    public ConfigCoreCustomizavel(TIPOS_CONFIG pTipoConfig, String pNomeCliente, String pNomeProjeto, String pNomeGrupoProjeto, Class< ? extends ConfigPermissaoAbstratoSBCore> pPermissao,
            Class... fabricasDeAcoes) {

        switch (pTipoConfig) {
            case CORE_DEVELOPER_PADRAO:

                setCliente(pNomeCliente);
                setGrupoProjeto(pNomeGrupoProjeto);
                setNomeProjeto(pNomeProjeto);

                setDiretorioBase("SuperBits_FrameWork");
                setUrlJira("https://santaClara.atlassian.net");
                setCentralDeEventos(CentralLogEventosArqTextoGenerica.class);
                setCentralMEnsagens(CentramMensagemProgramadorMsgStop.class);
                setClasseErro(ErroSBCoreDeveloperSopMessagem.class);
                setClasseConfigPermissao(pPermissao);
                setFabricaDeAcoes(fabricasDeAcoes);
                setControleDeSessao(ControleDeSessaoPadrao.class);

                break;
            case WEBPAGINAS_DEVELOPER_PADRAO:
                setCliente(pNomeCliente);
                setGrupoProjeto("SuperBits_FrameWork");
                setNomeProjeto(pNomeProjeto);
                setDiretorioBase("SB_FRAMEWORK");
                setCentralDeEventos(CentralLogEventosArqTextoGenerica.class);

                setClasseErro(ErroSBCoreFW.class);
                setControleDeSessao(ControleDeSessaoPadrao.class);
                break;
            default:
                throw new AssertionError(pTipoConfig.name());

        }

    }

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

    public final ConfigCoreCustomizavel setCentralMEnsagens(Class<? extends ItfCentralMensagens> centralMEnsagens) {
        this.centralMEnsagens = centralMEnsagens;
        return this;
    }

    public final ConfigCoreCustomizavel setClasseErro(Class<? extends InfoErroSBComAcoes> classeErro) {
        this.classeErro = classeErro;
        return this;
    }

    public final ConfigCoreCustomizavel setControleDeSessao(Class<? extends ItfControleDeSessao> controleDeSessao) {
        this.controleDeSessao = controleDeSessao;
        return this;
    }

    public final ConfigCoreCustomizavel setCentralDeEventos(Class<? extends ItfCentralEventos> centralDeEventos) {
        this.centralDeEventos = centralDeEventos;
        return this;
    }

    /**
     *
     * O nome do projeto identifica a pasta onde o projeto se encontra
     *
     * @param nomeProjeto
     * @return
     */
    public final ConfigCoreCustomizavel setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
        return this;
    }

    public ConfigCoreCustomizavel setEstadoAPP(SBCore.ESTADO_APP estadoAPP) {
        this.estadoAPP = estadoAPP;
        return this;
    }

    public final ConfigCoreCustomizavel setCliente(String cliente) {
        this.cliente = cliente;
        return this;
    }

    public final ConfigCoreCustomizavel setGrupoProjeto(String grupoProjeto) {
        this.grupoProjeto = grupoProjeto;
        return this;
    }

    /**
     *
     * O diretorio base é o diretorio que pode existir logo depois da pasta
     * source, agrupando diversos projetos
     *
     * @param diretorioBase
     * @return
     */
    public final ConfigCoreCustomizavel setDiretorioBase(String diretorioBase) {
        this.diretorioBase = diretorioBase;
        return this;
    }

    public final ConfigCoreCustomizavel setClasseConfigPermissao(Class<? extends ConfigPermissaoAbstratoSBCore> pConfigPermissao) {
        classeConfigPermissao = pConfigPermissao;
        return this;
    }

    public final ConfigCoreCustomizavel setFabricaDeAcoes(Class<? extends ItfFabricaAcoes>[] pAcoes) {
        acoesDoSistema = pAcoes;
        return this;
    }

    public final ConfigCoreCustomizavel setUrlJira(String urlJira) {
        this.urlJira = urlJira;
        return this;
    }

}
