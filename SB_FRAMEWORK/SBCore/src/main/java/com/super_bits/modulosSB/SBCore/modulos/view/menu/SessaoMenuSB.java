/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.menu;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoSessaoCategoria;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.TipoFonteUpload;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.validacaoRegistro.CampoInvalido;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Aramezena uma sessaão do sistema, contendo ações e outras sessoes
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 08/01/2016
 * @version 1.0
 *
 */
public class SessaoMenuSB implements ItfAcaoSessaoCategoria {

    private ItfAcaoDoSistema acaoSessao;
    private final List<ItfAcaoDoSistema> acoes;
    private List<SessaoMenuSB> sessoes;

    @Override
    public List<ItfAcaoDoSistema> getAcoes() {
        return (List) acoes;
    }

    public void addAcao(ItfAcaoDoSistema pAcao) {
        if (pAcao == null) {
            throw new UnsupportedOperationException("A ação adicionada na sessao não pode ser nula");
        }
        acoes.add(pAcao);

    }

    public SessaoMenuSB(ItfAcaoDoSistema pAcaoSessao) {
        acaoSessao = pAcaoSessao;
        if (acaoSessao.getIconeAcao() == null) {
            acaoSessao.setIconeAcao("fa fa-list-alt");
        }
        acoes = new ArrayList<>();
    }

    @Override

    public String getNomeAcao() {
        return acaoSessao.getNomeAcao();
    }

    @Override
    public String getIconeAcao() {
        return acaoSessao.getIconeAcao();
    }

    @Override
    public String getCor() {
        return acaoSessao.getCor();
    }

    @Override
    public String getDescricao() {
        return acaoSessao.getDescricao();
    }

    @Override
    public ItfModuloAcaoSistema getModulo() {
        return acaoSessao.getModulo();
    }

    @Override
    public boolean isPrecisaPermissao() {
        return acaoSessao.isPrecisaPermissao();
    }

    @Override
    public void setId(int pId) {
        acaoSessao.setId(pId);
    }

    public void setIdMetodo(int pID) {
        acaoSessao.setId(pID);
    }

    @Override
    public void setModuloAcaoSistema(ItfModuloAcaoSistema pmodulo) {
        acaoSessao.setModuloAcaoSistema(pmodulo);
    }

    @Override
    public String getImgPequena() {
        return acaoSessao.getImgPequena();
    }

    @Override
    public String getNomeCurto() {
        return acaoSessao.getNomeCurto();
    }

    @Override
    public int getId() {
        return acaoSessao.getId();
    }

    @Override
    public String getNomeCampo(FabCampos pInfocampo) {
        return null;
    }

    public Field getCampo(FabCampos pInfoCampo) {
        return null;
    }

    @Override
    public void uploadFoto(TipoFonteUpload pTipo, Object pRecurso) {
        acaoSessao.uploadFoto(pTipo, pRecurso);
    }

    @Override
    public void setIconeAcao(String pIcone) {
        acaoSessao.setIconeAcao(pIcone);
    }

    @Override
    public boolean isConfigurado() {
        return acaoSessao.isConfigurado();
    }

    public ItfAcaoDoSistema getAcaoPrincipal() {
        if (acaoSessao.isTemAcaoPrincipal()) {
            return ((ItfAcaoSecundaria) acaoSessao).getAcaoPrincipal();
        }
        return null;
    }

    public void validarAcao(boolean pValidarSeNaoConfigurado) {
        boolean resultado = true;
    }

    public void setIsAcaoPrincipal(boolean pisAcaoPrincipal) {
        System.out.println("Nada a fazer, tentativa de setar ação pricipal em uma sessao");
    }

    public void setAcaoPrincipal(ItfAcaoDoSistema pAcaoPrincipal) {
        System.out.println("Nada a fazer, tentativa de setar ação pricipal em uma sessao");
    }

    public ItfAcaoDoSistema getAcaoSessao() {
        return acaoSessao;
    }

    public void setAcaoSessao(ItfAcaoDoSistema acaoSessao) {
        this.acaoSessao = acaoSessao;
    }

    public void setSessoes(List<SessaoMenuSB> sessoes) {
        this.sessoes = sessoes;
    }

    @Override
    public String getNomeUnico() {
        return acaoSessao.getNomeUnico();
    }

    @Override
    public String getNomeEnumOriginal() {
        return acaoSessao.getNomeEnumOriginal();
    }

    public boolean isCaminhoAutomaticoXHTML() {
        return false;

    }

    @Override
    public List<ItfAcaoSessaoCategoria> getSessoes() {
        return (List) sessoes;

    }

    @Override
    public void setDescricao(String pDescricao) {
        acaoSessao.setDescricao(pDescricao);
    }

    public void configurarPropriedadesBasicas(ItfAcaoDoSistema pAcaoDoSistema) {
        acaoSessao.configurarPropriedadesBasicas(pAcaoDoSistema);
    }

    @Override
    public boolean isUmaAcaoFormulario() {
        return acaoSessao.isUmaAcaoFormulario();
    }

    @Override
    public void setNomeAcao(String pNome) {
        acaoSessao.setNomeAcao(pNome);
    }

    @Override
    public FabTipoAcaoSistema getTipoAcaoSistema() {
        return acaoSessao.getTipoAcaoSistema();
    }

    @Override
    public ItfFabricaAcoes getEnumAcaoDoSistema() {
        return acaoSessao.getEnumAcaoDoSistema();
    }

    @Override
    public boolean isTemAcaoPrincipal() {
        return acaoSessao.isTemAcaoPrincipal();
    }

    @Override
    public String getIdDescritivoJira() {
        return acaoSessao.getIdDescritivoJira();
    }

    @Override
    public void setIdDescritivoJira(String pIdJira) {
        acaoSessao.setIdDescritivoJira(pIdJira);
    }

    @Override
    public void setPrecisaPermissao(boolean pPermissao) {
        acaoSessao.setPrecisaPermissao(pPermissao);
    }

    @Override
    public FabTipoAcaoSistemaGenerica getTipoAcaoGenerica() {
        return acaoSessao.getTipoAcaoGenerica();
    }

    @Override
    public boolean isUmaAcaoGenerica() {
        return acaoSessao.isUmaAcaoGenerica();
    }

    @Override
    public boolean isUmaAcaoGestaoDominio() {
        return acaoSessao.isUmaAcaoGestaoDominio();
    }

    @Override
    public String getNome() {
        return acaoSessao.getNome();
    }

    @Override
    public void configIDPeloNome() {
        acaoSessao.configIDPeloNome();
    }

    @Override
    public String getNomeDoObjeto() {
        return acaoSessao.getNomeDoObjeto();
    }

    @Override
    public void setNome(String pNome) {
        acaoSessao.setNome(pNome);
    }

    @Override
    public List<ItfCampoInstanciado> getCamposInstaciadosInvalidos() {
        return acaoSessao.getCamposInstaciadosInvalidos();
    }

    @Override
    public ItfCampoInstanciado getCampoByNomeOuAnotacao(String pNome) {
        return acaoSessao.getCampoByNomeOuAnotacao(pNome);
    }

    @Override
    public List<CaminhoCampoReflexao> getEntidadesVinculadas() {
        return acaoSessao.getEntidadesVinculadas();
    }

    @Override
    public ItfBeanSimples getBeanSimplesPorNomeCampo(String pNomeCampo) {
        return acaoSessao.getBeanSimplesPorNomeCampo(pNomeCampo);
    }

    @Override
    public ItfBeanSimples getItemPorCaminhoCampo(CaminhoCampoReflexao pCaminho) {
        return acaoSessao.getItemPorCaminhoCampo(pCaminho);
    }

    @Override
    public List<CampoInvalido> getCamposInvalidos() {
        return acaoSessao.getCamposInvalidos();
    }

    @Override
    public Field getCampoReflexaoByAnotacao(FabCampos pInfoCampo) {
        return acaoSessao.getCampoReflexaoByAnotacao(pInfoCampo);
    }

    @Override
    public boolean isUmaAcaoDeEntidade() {
        return acaoSessao.isUmaAcaoDeEntidade();
    }

    @Override
    public boolean isUmaAcaoSessaoMenu() {
        return true;
    }

    @Override
    public String getNomeUnicoSlug() {
        return getNomeCurto() + "-" + getId();
    }

    @Override
    public boolean isUmaAcaoController() {
        return acaoSessao.isUmaAcaoController();
    }

    @Override
    public String getNomeDominio() {
        return acaoSessao.getNomeDominio();
    }

    @Override
    public ItfAcaoFormulario getComoFormulario() {
        return acaoSessao.getComoFormulario();
    }

    @Override
    public ItfAcaoGerenciarEntidade getComoGestaoEntidade() {
        return acaoSessao.getComoGestaoEntidade();
    }

    @Override
    public ItfAcaoController getComoController() {
        return acaoSessao.getComoController();
    }

    @Override
    public ItfAcaoSecundaria getComoSecundaria() {
        return acaoSessao.getComoSecundaria();

    }

    @Override
    public ItfAcaoFormularioEntidade getComoFormularioEntidade() {
        return acaoSessao.getComoFormularioEntidade();
    }

    @Override
    public ItfAcaoControllerEntidade getComoControllerEntidade() {
        return acaoSessao.getComoControllerEntidade();
    }

    @Override
    public List<ItfBeanSimples> getListaPorCaminhoCampo(CaminhoCampoReflexao pCaminho) {
        return acaoSessao.getListaPorCaminhoCampo(pCaminho);
    }

    @Override
    public ItfCampoInstanciado getCampoByCaminhoCampo(CaminhoCampoReflexao pNome) {
        return acaoSessao.getCampoByCaminhoCampo(pNome);
    }

    @Override
    public Object getValorCampoByCaminhoCampo(CaminhoCampoReflexao pNome) {
        return acaoSessao.getValorCampoByCaminhoCampo(pNome);
    }

    @Override
    public ItfAcaoGerenciarEntidade getAcaoDeGestaoEntidade() {
        return acaoSessao.getAcaoDeGestaoEntidade();
    }

    @Override
    public void adicionarItemNaLista(String nomeDaLista) {
        throw new UnsupportedOperationException("O método adicionar item em listas do item não foi implementado para sessão de menu "); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIconeDaClasse() {
        return "fa fa-sitemap";
    }

    @Override
    public String getXhtmlVisao() {
        return SBCore.getCentralVisualizacao().getCaminhoXhtmlItemCard(this.getClass());
    }

    @Override
    public boolean isTemCampoAnotado(FabCampos pCampo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
