/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.modulosSB.SBCore.modulos.Controller.ConfigPermissaoSBCoreAbstrato;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.interfaces.ItfCentralDeArquivos;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfCentralMensagens;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.InfoErroSBComAcoes;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfCentralComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.logeventos.ItfCentralEventos;
import com.super_bits.modulosSB.SBCore.modulos.sessao.Interfaces.ItfControleDeSessao;
import com.super_bits.modulosSB.SBCore.modulos.view.ItfServicoVisualizacao;

/**
 *
 * @author salvioF
 */
public interface ItfConfiguracaoCoreCustomizavel extends ItfConfiguracaoCoreSomenteLeitura {

    public void setCentralMEnsagens(Class<? extends ItfCentralMensagens> centralMEnsagens);

    public void setClasseErro(Class<? extends InfoErroSBComAcoes> classeErro);

    public void setControleDeSessao(Class<? extends ItfControleDeSessao> controleDeSessao);

    public void setCentralDeEventos(Class<? extends ItfCentralEventos> centralDeEventos);

    public void setCentralDeArquivos(Class<? extends ItfCentralDeArquivos> centralDeArquivos);

    /**
     *
     * O nome do projeto identifica a pasta onde o projeto se encontra
     *
     * @param nomeProjeto
     * @return
     */
    public void setNomeProjeto(String nomeProjeto);

    public void setEstadoAPP(SBCore.ESTADO_APP estadoAPP);

    public void setCliente(String cliente);

    public void setGrupoProjeto(String grupoProjeto);

    /**
     *
     * O diretorio base é o diretorio que pode existir logo depois da pasta
     * source, agrupando diversos projetos
     *
     * @param diretorioBase
     * @return
     */
    public void setDiretorioBase(String diretorioBase);

    public void setClasseConfigPermissao(Class<? extends ConfigPermissaoSBCoreAbstrato> pConfigPermissao);

    public void setFabricaDeAcoes(Class<? extends ItfFabricaAcoes>[] pAcoes);

    public void setUrlJira(String urlJira);

    public void setNomeSocial(String pNomeSocial);

    public void setServicoVisualizacao(Class<? extends ItfServicoVisualizacao> classeVisualizacao);

    public void setCentralComunicacao(Class<? extends ItfCentralComunicacao> pCentral);

}
