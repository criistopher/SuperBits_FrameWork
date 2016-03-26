/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.TipoFonteUpload;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemGenerico;
import java.lang.reflect.Field;

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
 *
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 30/01/2016
 * @version 1.0
 */
public class AcaoManagedBean extends ItemGenerico implements ItfAcaoDoSistema {

    private final ItfAcaoDoSistema acaoOriginal;

    private final String urlDeAcesso;

    public AcaoManagedBean(ItfAcaoDoSistema pAcaoDoSistema, ItfB_Pagina pagina) {
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
    public String getXHTMLAcao() {
        return acaoOriginal.getXHTMLAcao();
    }

    @Override
    public ItfModuloAcaoSistema getModulo() {
        return acaoOriginal.getModulo();
    }

    @Override
    public int getIdMetodo() {
        return acaoOriginal.getIdMetodo();
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
    public void setIcone(String pIcone) {
        acaoOriginal.setIcone(pIcone);
    }

    @Override
    public void setIdMetodo(int pID) {
        acaoOriginal.setIdMetodo(pID);
    }

    @Override
    public void setModuloAcaoSistema(ItfModuloAcaoSistema pmodulo) {
        acaoOriginal.setModuloAcaoSistema(pmodulo);
    }

    @Override
    public boolean isTipoAcaoDireta() {
        return acaoOriginal.isTipoAcaoDireta();
    }

    @Override
    public boolean isTipoAcao() {
        return acaoOriginal.isTipoAcao();
    }

    @Override
    public boolean isTipoSessaoMenu() {
        return acaoOriginal.isTipoSessaoMenu();
    }

    @Override
    public boolean isConfigurado() {
        return acaoOriginal.isConfigurado();
    }

    @Override
    public ItfAcaoDoSistema getAcaoPrincipal() {
        return acaoOriginal.getAcaoPrincipal();
    }

    @Override
    public void validarAcao(boolean pValidarSeNaoConfigurado) {
        acaoOriginal.validarAcao(pValidarSeNaoConfigurado);
    }

    @Override
    public boolean isUmaAcaoPrincipal() {
        return acaoOriginal.isUmaAcaoPrincipal();
    }

    @Override
    public void setIsAcaoPrincipal(boolean pisAcaoPrincipal) {
        acaoOriginal.setAcaoPrincipal(acaoOriginal);
    }

    @Override
    public void setAcaoPrincipal(ItfAcaoDoSistema pAcaoPrincipal) {
        acaoOriginal.setAcaoPrincipal(pAcaoPrincipal);
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

    public ItfAcaoDoSistema getAcaoOriginal() {
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

    @Override
    public FabTipoAcaoSistemaGenerica getTipoAcaoSistema() {
        return null;
    }

    @Override
    public boolean isCaminhoAutomaticoXHTML() {
        return false;
    }

}
