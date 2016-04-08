/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
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

    public FabTipoAcaoSistemaGenerica getTipoAcao() {
        return null;
    }

    @Override
    public void setDescricao(String pDescricao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FabTipoAcaoSistema getTipoAcaoSistema() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isTemAcaoPrincipal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void configurarPropriedadesBasicas(ItfAcaoDoSistema pAcaoDoSistema) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAcaoFormulario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNome(String pNome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
