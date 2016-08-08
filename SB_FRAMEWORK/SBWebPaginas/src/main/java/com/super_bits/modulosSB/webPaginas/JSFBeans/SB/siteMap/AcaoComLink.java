/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.sun.source.tree.IfTree;
import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.TipoFonteUpload;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemGenerico;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import java.lang.reflect.Field;
import javax.persistence.Transient;

/**
 *
 * As ações ManagedBen são ações principais do sistema, que contem uma pagina de
 * gestão MB_ vinculadas
 *
 * Todas as ações principais devem conter um managedBen vinculado.
 *
 * A criação destas ações são criadas dinamicamente no sistema e ficam
 * disponíveis logo após a criação de seus respectivos Managed Benas.
 *
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 30/01/2016
 * @version 1.0
 */
public class AcaoComLink extends ItemGenerico implements ItfAcaoDoSistema {

    @Transient
    private final ItfAcaoFormulario acaoOriginal;
    @Transient
    private final String urlDeAcesso;

    public AcaoComLink(ItfAcaoFormulario pAcaoDoSistema, ItfB_Pagina pagina) {
        acaoOriginal = pAcaoDoSistema;
        urlDeAcesso = pagina.getUrlPadrao();
    }

    @Override
    public String getNomeAcao() {
        return acaoOriginal.getNomeAcao();
    }

    @Override
    public String getIconeAcao() {
        return acaoOriginal.getIconeAcao();
    }

    @Override
    public String getCor() {
        return acaoOriginal.getCor();
    }

    @Override
    public String getDescricao() {
        return acaoOriginal.getDescricao();
    }

    @Override
    public ItfModuloAcaoSistema getModulo() {
        return acaoOriginal.getModulo();
    }

    @Override
    public boolean isPrecisaPermissao() {
        return acaoOriginal.isPrecisaPermissao();
    }

    @Override
    public void setId(int pId) {
        acaoOriginal.setId(pId);
    }

    @Override
    public void setIconeAcao(String pIcone) {
        acaoOriginal.setIconeAcao(pIcone);
    }

    @Override
    public void setModuloAcaoSistema(ItfModuloAcaoSistema pmodulo) {
        acaoOriginal.setModuloAcaoSistema(pmodulo);
    }

    @Override
    public boolean isConfigurado() {
        return acaoOriginal.isConfigurado();
    }

    @Override
    public String getImgPequena() {
        return acaoOriginal.getImgPequena();
    }

    @Override
    public String getNomeCurto() {
        return acaoOriginal.getNomeCurto();
    }

    @Override
    public int getId() {
        return acaoOriginal.getId();
    }

    @Override
    public String getNomeCampo(FabCampos pInfocampo) {
        return acaoOriginal.getNomeCampo(pInfocampo);
    }

    @Override
    public Field getCampoReflexaoByAnotacao(FabCampos pInfoCampo) {
        return acaoOriginal.getCampoReflexaoByAnotacao(pInfoCampo);
    }

    @Override
    public void uploadFoto(TipoFonteUpload pTipo, Object pRecurso) {
        acaoOriginal.uploadFoto(pTipo, pRecurso);
    }

    public String getUrlDeAcesso() {
        return urlDeAcesso;
    }

    public ItfAcaoFormulario getAcaoOriginal() {
        return acaoOriginal;
    }

    @Override
    public String getNomeUnico() {
        return acaoOriginal.getNomeUnico();
    }

    @Override
    public String getNomeEnumOriginal() {
        return acaoOriginal.getNomeEnumOriginal();
    }

    public FabTipoAcaoSistemaGenerica getTipoAcao() {
        return null;
    }

    @Override
    public void setDescricao(String pDescricao) {
        acaoOriginal.setDescricao(pDescricao);
    }

    @Override
    public FabTipoAcaoSistema getTipoAcaoSistema() {
        return acaoOriginal.getTipoAcaoSistema();
    }

    @Override
    public boolean isTemAcaoPrincipal() {
        return acaoOriginal.isTemAcaoPrincipal();
    }

    @Override
    public void configurarPropriedadesBasicas(ItfAcaoDoSistema pAcaoDoSistema) {
        acaoOriginal.configurarPropriedadesBasicas(pAcaoDoSistema);
    }

    @Override
    public boolean isUmaAcaoFormulario() {
        return acaoOriginal.isUmaAcaoFormulario();
    }

    @Override
    public String getNome() {
        return acaoOriginal.getNome();
    }

    @Override
    public void setNome(String pNome) {
        acaoOriginal.setNome(pNome);
    }

    @Override
    public void setNomeAcao(String pNome) {
        acaoOriginal.setNomeAcao(pNome);
    }

    @Override
    public String getIdDescritivoJira() {
        return acaoOriginal.getIdDescritivoJira();
    }

    @Override
    public void setIdDescritivoJira(String pIdJira) {
        acaoOriginal.setIdDescritivoJira(pIdJira);
    }

    @Override
    public void setPrecisaPermissao(boolean pPermissao) {
        acaoOriginal.setPrecisaPermissao(pPermissao);
    }

    @Override
    public FabTipoAcaoSistemaGenerica getTipoAcaoGenerica() {
        return FabTipoAcaoSistemaGenerica.GERENCIAR_DOMINIO;
    }

    @Override
    public boolean isUmaAcaoGenerica() {
        return true;
    }

    @Override
    public boolean isUmaAcaoGestaoDominio() {
        return true;
    }

    @Override
    public ItfFabricaAcoes getEnumAcaoDoSistema() {
        return acaoOriginal.getEnumAcaoDoSistema();
    }

    @Override
    public boolean isUmaAcaoSessaoMenu() {
        return false;
    }

    @Override
    public String getNomeUnicoSlug() {
        return getNomeCurto() + "-" + getId();
    }

    @Override
    public boolean isUmaAcaoDeEntidade() {
        return acaoOriginal.isUmaAcaoDeEntidade();
    }

    @Override
    public boolean isUmaAcaoController() {
        return acaoOriginal.isUmaAcaoController();
    }

    @Override
    public String getNomeDominio() {
        return acaoOriginal.getNomeDominio();
    }

    @Override
    public ItfAcaoFormulario getComoFormulario() {
        return acaoOriginal.getComoFormulario();
    }

    @Override
    public ItfAcaoGerenciarEntidade getComoGestaoEntidade() {
        return acaoOriginal.getComoGestaoEntidade();
    }

    @Override
    public ItfAcaoController getComoController() {
        return acaoOriginal.getComoController();
    }

    @Override
    public ItfAcaoSecundaria getComoSecundaria() {
        throw new UnsupportedOperationException("Uma ação de Managed bean não pode ser tratada como ação secundaria"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoFormularioEntidade getComoFormularioEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoControllerEntidade getComoControllerEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
