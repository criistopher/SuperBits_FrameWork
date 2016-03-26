/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.menu;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.TipoFonteUpload;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemGenerico;
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
public class SessaoMenuSB extends ItemGenerico implements ItfAcaoDoSistema {

    private ItfAcaoDoSistema acaoSessao;
    private List<ItfAcaoDoSistema> acoes;
    private List<SessaoMenuSB> sessoes;

    public List<ItfAcaoDoSistema> getAcoes() {
        return acoes;
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
            acaoSessao.setIcone("fa fa-list-alt");
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
    public String getXHTMLAcao() {
        return acaoSessao.getXHTMLAcao();
    }

    @Override
    public ItfModuloAcaoSistema getModulo() {
        return acaoSessao.getModulo();
    }

    @Override
    public int getIdMetodo() {
        return acaoSessao.getIdMetodo();
    }

    @Override
    public boolean isPrecisaPermissao() {
        return acaoSessao.isPrecisaPermissao();
    }

    @Override
    public void setId(int pId) {
        acaoSessao.setId(pId);
    }

    @Override
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
        return getNomeCampo(pInfocampo);
    }

    @Override
    public Field getCampoReflexaoByAnotacao(FabCampos pInfoCampo) {
        return getCampoReflexaoByAnotacao(pInfoCampo);
    }

    @Override
    public void uploadFoto(TipoFonteUpload pTipo, Object pRecurso) {
        acaoSessao.uploadFoto(pTipo, pRecurso);
    }

    @Override
    public void setIcone(String pIcone) {
        acaoSessao.setIcone(pIcone);
    }

    @Override
    public boolean isTipoAcaoDireta() {
        return getXHTMLAcao() == null;
    }

    @Override
    public boolean isTipoAcao() {
        return false;
    }

    @Override
    public boolean isTipoSessaoMenu() {
        return true;
    }

    @Override
    public boolean isConfigurado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoDoSistema getAcaoPrincipal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void validarAcao(boolean pValidarSeNaoConfigurado) {
        boolean resultado = true;
    }

    @Override
    public boolean isUmaAcaoPrincipal() {
        return false;
    }

    @Override
    public void setIsAcaoPrincipal(boolean pisAcaoPrincipal) {
        System.out.println("Nada a fazer, tentativa de setar ação pricipal em uma sessao");
    }

    @Override
    public void setAcaoPrincipal(ItfAcaoDoSistema pAcaoPrincipal) {
        System.out.println("Nada a fazer, tentativa de setar ação pricipal em uma sessao");
    }

    public ItfAcaoDoSistema getAcaoSessao() {
        return acaoSessao;
    }

    public void setAcaoSessao(ItfAcaoDoSistema acaoSessao) {
        this.acaoSessao = acaoSessao;
    }

    public List<SessaoMenuSB> getSessoes() {
        return sessoes;
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

    @Override
    public FabTipoAcaoSistemaGenerica getTipoAcaoSistema() {
        return null;
    }

    @Override
    public boolean isCaminhoAutomaticoXHTML() {
        return false;
    }

}
