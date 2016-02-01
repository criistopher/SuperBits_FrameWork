/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.model.AcaoDoSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.TipoFonteUpload;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.InfoPagina;
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
public class AcaoManagedBean implements ItfAcaoDoSistema {

    private AcaoDoSistema acaoVinculada;
    private ItfB_Pagina paginaVinculada;
    private InfoPagina infoPagina;
    private String urlDeAcesso;

    public AcaoManagedBean(AcaoDoSistema pAcaoDoSistema) {

    }

    @Override
    public String getNomeAcao() {
        return acaoVinculada.getNomeAcao();
    }

    @Override
    public String getIconeAcao() {
        return acaoVinculada.getIconeAcao();
    }

    @Override
    public String getCor() {
        return acaoVinculada.getCor();
    }

    @Override
    public String getDescricao() {
        return acaoVinculada.getDescricao();
    }

    @Override
    public String getXHTMLAcao() {
        return acaoVinculada.getXHTMLAcao();
    }

    @Override
    public ItfModuloAcaoSistema getModulo() {
        return acaoVinculada.getModulo();
    }

    @Override
    public int getIdMetodo() {
        return acaoVinculada.getIdMetodo();
    }

    @Override
    public boolean isPrecisaPermissao() {
        return acaoVinculada.isPrecisaPermissao();
    }

    @Override
    public void setId(int pId) {
        acaoVinculada.setId(pId);
    }

    @Override
    public void setIcone(String pIcone) {
        acaoVinculada.setIcone(pIcone);
    }

    @Override
    public void setIdMetodo(int pID) {
        acaoVinculada.setIdMetodo(pID);
    }

    @Override
    public void setModuloAcaoSistema(ItfModuloAcaoSistema pmodulo) {
        acaoVinculada.setModuloAcaoSistema(pmodulo);
    }

    @Override
    public boolean isTipoAcaoDireta() {
        return acaoVinculada.isTipoAcaoDireta();
    }

    @Override
    public boolean isTipoAcao() {
        return acaoVinculada.isTipoAcao();
    }

    @Override
    public boolean isTipoSessaoMenu() {
        return acaoVinculada.isTipoSessaoMenu();
    }

    @Override
    public boolean isConfigurado() {
        return acaoVinculada.isConfigurado();
    }

    @Override
    public ItfAcaoDoSistema getAcaoPrincipal() {
        return acaoVinculada.getAcaoPrincipal();
    }

    @Override
    public void validarAcao(boolean pValidarSeNaoConfigurado) {
        acaoVinculada.validarAcao(pValidarSeNaoConfigurado);
    }

    @Override
    public boolean isAcaoPrincipal() {
        return acaoVinculada.isAcaoPrincipal();
    }

    @Override
    public void setIsAcaoPrincipal(Boolean pisAcaoPrincipal) {
        acaoVinculada.setAcaoPrincipal(acaoVinculada);
    }

    @Override
    public void setAcaoPrincipal(ItfAcaoDoSistema pAcaoPrincipal) {
        acaoVinculada.setAcaoPrincipal(pAcaoPrincipal);
    }

    @Override
    public String getImgPequena() {
        return acaoVinculada.getImgPequena();
    }

    @Override
    public String getNomeCurto() {
        return acaoVinculada.getNomeCurto();
    }

    @Override
    public int getId() {
        return acaoVinculada.getId();
    }

    @Override
    public String getNomeCampo(FabCampos pInfocampo) {
        return acaoVinculada.getNomeCampo(pInfocampo);
    }

    @Override
    public Field getCampo(FabCampos pInfoCampo) {
        return acaoVinculada.getCampo(pInfoCampo);
    }

    @Override
    public void uploadFoto(TipoFonteUpload pTipo, Object pRecurso) {
        acaoVinculada.uploadFoto(pTipo, pRecurso);
    }

}
